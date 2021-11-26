package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.constant.AccountConstant;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.domain.factory.UserFactory;
import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.domain.service.UserService;
import com.mst.csuserservice.domain.service.strategy.UserLoginStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    private final Map<Integer, Function<UserLoginQuery, Optional<Account>>> loginDispatcher = new HashMap<>(UserConstant.TOKEN_MAP_CAPACITY);

    public UserServiceImpl(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    /**
     * 注册.
     * @param   userCreateCommand  create user command
     * @return  User
     */
    @Override
    public User register(UserCreateCommand userCreateCommand) {
        // 补全用户信息，构建用户实体.
        User user = userFactory.buildUser(userCreateCommand);
        // 将用户实体持久化入数据库.
        User register = userRepository.saveUser(user);
        // 将user id按策略生成openCode，并持久化入数据库.
        String openCode = userFactory.buildOpenCode(register.getId());
        Account account = userFactory.buildAccount(openCode, register.getId());
        userRepository.saveAccount(account);
        // 返回用户
        return register;
    }

    /**
     * 登录.
     * @param   userLoginQuery  login user query
     * @return  SaTokenInfo
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SaTokenInfo login(UserLoginQuery userLoginQuery) {
        SaTokenInfo saTokenInfo = null;
        // 当前密码加密
        String secretKey = SaSecureUtil.md5BySalt(userLoginQuery.getPassword(), UserConstant.PWD_SALT);
        // 重新设置loginQuery密码.
        userLoginQuery.setPassword(secretKey);
        // 执行登录逻辑校验，得到账户容器.
        Optional<Account> accountOptional = getLoginResult(userLoginQuery);
        // 如果账户存在
        if (accountOptional.isPresent()) {
            // 获取当前账户
            Account account = accountOptional.get();
            if (account.getOpenCode() != null) {
                // 执行登录
                StpUtil.login(account.getUserId());
                // 获取当前登录用户的token info
                saTokenInfo = StpUtil.getTokenInfo();
            }
        }
        return saTokenInfo;
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
                account = userLoginStrategy.findAccount(AccountConstant.PHONE_TYPE, uid.get());
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
