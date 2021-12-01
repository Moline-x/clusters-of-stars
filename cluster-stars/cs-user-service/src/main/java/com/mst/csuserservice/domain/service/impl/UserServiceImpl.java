package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.constant.AccountConstant;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.domain.bo.UserLoginBO;
import com.mst.csuserservice.domain.enums.Role;
import com.mst.csuserservice.domain.factory.UserFactory;
import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.domain.service.UserService;
import com.mst.csuserservice.domain.service.strategy.UserLoginStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Function;

/**
 * @author Molin
 * @date   2021-11-19  22:55
 * 用户领域服务
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserFactory userFactory;

    private UserLoginStrategy userLoginStrategy;

    private final UserGetPermission userGetPermission;

    private final Map<Integer, Function<UserLoginQuery, Optional<Account>>> loginDispatcher = new HashMap<>(UserConstant.TOKEN_MAP_CAPACITY);

    public UserServiceImpl(UserRepository userRepository,
                           UserFactory userFactory,
                           UserGetPermission userGetPermission) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.userGetPermission = userGetPermission;
    }

    /**
     * 注册.
     * @param   userCreateCommand  create user command
     * @return  User
     */
    @Override
    public User register(UserCreateCommand userCreateCommand) {
        // 判断是否已注册.
        User register = null;
        Optional<User> user = userRepository.findByMobileAndEmail(userCreateCommand.getMobile(), userCreateCommand.getEmail());
        // 如果用户已存在，则直接响应回应用层，如果不存在，则保存用户信息.
        if (user.isEmpty()) {
            // 补全用户信息，构建用户实体，将用户实体持久化入数据库.
            register = userRepository.saveUser(userFactory.buildUser(userCreateCommand));
            // 将user id按策略生成openCode，并持久化入数据库.
            String openCode = userFactory.buildOpenCode(register.getId());
            Account account = userFactory.buildAccount(openCode, register.getId());
            userRepository.saveAccount(account);
            // 建立用户与角色关系.
            userRepository.saveRoleUser(userFactory.buildUserRole(register.getId(), Role.CUSTOMER.getCode()));
        }
        return register;
    }

    /**
     * 登录.
     * @param   userLoginQuery  login user query
     * @return  token和permission list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserLoginBO login(UserLoginQuery userLoginQuery) {
        SaTokenInfo saTokenInfo = null;
        List<String> permissionList = new ArrayList<>();
        // 重新设置loginQuery密码.
        userLoginQuery.setPassword(SaSecureUtil.md5BySalt(userLoginQuery.getPassword(), UserConstant.PWD_SALT));
        // 执行登录逻辑校验，得到账户容器.
        Optional<Account> accountOptional = getLoginResult(userLoginQuery);
        // 如果账户存在
        if (accountOptional.isPresent()) {
            // 获取当前账户
            Account account = accountOptional.get();
            Long userId = account.getUserId();
            // 执行登录
            Optional.ofNullable(account.getUserId()).ifPresent(StpUtil::login);
            // 获取权限列表
            permissionList = userGetPermission.getPermissionList(userId, null);
            // 获取当前登录用户的token info
            saTokenInfo = StpUtil.getTokenInfo();
        }
        return UserLoginBO.builder().tokenInfo(saTokenInfo).permissionsList(permissionList).build();
    }

    /**
     * 后台创建用户服务.
     * @param  userCreateCommand  create user command
     * @return User
     */
    @Override
    public User createUser(UserCreateCommand userCreateCommand) {
        // 判断后台是否已添加过该用户.
        User newUser = null;
        Optional<User> user = userRepository.findByMobileAndEmail(userCreateCommand.getMobile(), userCreateCommand.getEmail());
        // 如果用户已存在，则直接响应回应用层，如果不存在，则保存用户信息.
        if (user.isEmpty()) {
            // 补全用户信息，构建用户实体，将用户实体持久化入数据库.
            newUser = userRepository.saveUser(userFactory.buildUser(userCreateCommand));
            Long userId = newUser.getId();
            // 将user id按策略生成openCode，并持久化入数据库.
            String openCode = userFactory.buildOpenCode(userId);
            Account account = userFactory.buildAccount(openCode, userId);
            userRepository.saveAccount(account);
            // 根据角色Id建立用户与角色关系.
            userRepository.saveRoleUser(userFactory.buildUserRole(userId, userCreateCommand.getRoleId()));
        }
        return newUser;
    }

    /**
     * 登录逻辑分发初始化.
     */
    @PostConstruct
    public void loginDispatcherInit() {
        // 手机登录逻辑校验.
        loginDispatcher.put(AccountConstant.PHONE_TYPE, u -> {
            userLoginStrategy = new UserMobileLoginStrategy(userRepository);
            Optional<Long> uid = userLoginStrategy.findUser(u.getMobile(), u.getPassword());
            Optional<Account> account = Optional.empty();
            if (uid.isPresent()) {
                account = userLoginStrategy.findAccount(AccountConstant.PHONE_TYPE, uid.get());
            }
            return account;
        });
        // 电子邮箱登录逻辑校验.
        loginDispatcher.put(AccountConstant.EMAIL_TYPE, u -> {
            userLoginStrategy = new UserEmailLoginStrategy(userRepository);
            Optional<Long> uid = userLoginStrategy.findUser(u.getEmail(), u.getPassword());
            Optional<Account> account = Optional.empty();
            if (uid.isPresent()) {
                account = userLoginStrategy.findAccount(AccountConstant.EMAIL_TYPE, uid.get());
            }
            return account;
        });
    }

    /**
     * 策略模式，根据登录策略执行登录逻辑校验.
     * @param  userLoginQuery  用户查询对象
     * @return Optional account
     */
    public Optional<Account> getLoginResult(UserLoginQuery userLoginQuery) {
        // 根据登录策略获取登录逻辑校验.
        Function<UserLoginQuery, Optional<Account>> loginResult = loginDispatcher.get(userLoginQuery.getLoginType());
        // 执行登录逻辑校验.
        if (loginResult != null) {
            return loginResult.apply(userLoginQuery);
        }
        return Optional.empty();
    }
}
