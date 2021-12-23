package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.google.common.collect.Lists;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.command.UserUnBindCommand;
import com.mst.csuserservice.controller.cqe.command.UserUpdateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.domain.bo.UserLoginBO;
import com.mst.csuserservice.domain.enums.AccountCategory;
import com.mst.csuserservice.domain.enums.Role;
import com.mst.csuserservice.domain.factory.UserFactory;
import com.mst.csuserservice.domain.mapper.UserMapper;
import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.LoginLog;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.domain.service.UserService;
import com.mst.csuserservice.domain.service.strategy.UserLoginStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

    private final UserGetPermission userGetPermission;

    private final UserMapper userMapper;

    private final Map<Integer, Function<UserLoginQuery, Optional<Account>>> loginDispatcher = new HashMap<>(UserConstant.TOKEN_MAP_CAPACITY);

    public UserServiceImpl(UserRepository userRepository,
                           UserFactory userFactory,
                           UserGetPermission userGetPermission,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
        this.userGetPermission = userGetPermission;
        this.userMapper = userMapper;
    }

    /**
     * 注册.
     * @param   userCreateCommand  create user command
     * @return  User
     */
    @Override
    public User register(UserCreateCommand userCreateCommand) {
        // 判断是否已注册.
        Optional<User> user = userRepository.findByMobileAndEmail(userCreateCommand.getMobile(), userCreateCommand.getEmail());
        // 如果用户已存在，则直接响应回应用层, 如果不存在，则保存用户信息.
        return user.isEmpty() ? user.orElseGet(() -> saveUserInfo(userCreateCommand)) : null;
    }

    /**
     * 存储用户信息.
     * @param  userCreateCommand create user command
     * @return User
     */
    private User saveUserInfo(UserCreateCommand userCreateCommand) {
        // 补全用户信息，构建用户实体，将用户实体持久化入数据库.
        var register = userRepository.saveUser(userFactory.buildUser(userCreateCommand));
        Long id = Optional.ofNullable(register).map(User::getId).orElse(UserConstant.ROLE_SUPER_ADMIN);
        // 按策略生成openCode，并持久化入数据库.
        userRepository.saveAccount(userFactory.buildAccount(userFactory.buildOpenCode(id), id));
        // 建立用户与角色关系.
        userRepository.saveRoleUser(userFactory.buildUserRole(id, Role.CUSTOMER.getCode()));
        return register;
    }

    /**
     * 登录.
     * @param   userLoginQuery  login user query
     * @return  token和permission list
     */
    @Override
    public UserLoginBO login(UserLoginQuery userLoginQuery) {

        // 重新设置loginQuery密码.
        userLoginQuery.setPassword(SaSecureUtil.md5BySalt(userLoginQuery.getPassword(), UserConstant.PWD_SALT));
        // 执行登录逻辑校验，得到账户容器.
        Optional<Account> accountOptional = getLoginResult(userLoginQuery);
        // 如果账户存在
        Optional<Long> userId = accountOptional.map(Account::getUserId);
        // 执行登录
        userId.ifPresent(StpUtil::login);
        // 获取当前登录用户的token info
        Optional<SaTokenInfo> saTokenInfo = userId.map(u -> StpUtil.getTokenInfo());

        return UserLoginBO.builder()
                .tokenInfo(saTokenInfo)
                .loginId(userId).build();
    }

    /**
     * 记录登录日志.
     * @param  loginLog login log
     * @param  userId   user id
     */
    @Override
    public void saveLoginLog(LoginLog loginLog, Long userId) {
        loginLog.setUserId(userId);
        loginLog.setLoginTime(new Date());
        loginLog.setLoginType(UserConstant.ENABLE_CODE);
        // 记录登录日志.
        userMapper.saveLoginLog(loginLog);
    }

    /**
     * 获取权限列表.
     *
     * @param userId user id
     * @return permission list
     */
    @Override
    public List<String> getPermissionList(Long userId) {

        return Optional.ofNullable(userId)
                .map(u -> userGetPermission.getPermissionList(u, null))
                .orElseGet(Lists::newArrayList);
    }

    /**
     * 获取角色列表.
     *
     * @param userId user id
     * @return role list
     */
    @Override
    public List<String> getRoleList(Long userId) {

        return Optional.ofNullable(userId)
                .map(u -> userGetPermission.getRoleList(u, null))
                .orElseGet(Lists::newArrayList);
    }

    /**
     * 后台创建用户服务.
     * @param  userCreateCommand  create user command
     * @return User
     */
    @Override
    public User createUser(UserCreateCommand userCreateCommand) {
        return register(userCreateCommand);
    }

    /**
     * 根据用户id删除用户操作.
     * @param  userIds  user id list
     * @return true or false
     */
    @Override
    public Boolean removeUser(List<Long> userIds) {
        // 1. 删除用户账户.
        int removeAccountCount = userMapper.removeAccount(userIds, UserConstant.DELETED);
        // 2. 删除用户.
        int removeUserCount = UserConstant.BOUNDARY_COUNT;
        if (removeAccountCount > UserConstant.BOUNDARY_COUNT) {
            removeUserCount = userMapper.removeUser(userIds, UserConstant.DELETED);
        }
        return removeAccountCount > UserConstant.BOUNDARY_COUNT
                && removeUserCount > UserConstant.BOUNDARY_COUNT;
    }

    /**
     * 根据用户id或角色id解除绑定.
     * @param  userUnBindCommand  user unbind command
     * @return true or false
     */
    @Override
    public Boolean unBindUserAndRole(UserUnBindCommand userUnBindCommand) {
        int count = userMapper.unBindUserAndRole(userUnBindCommand, UserConstant.DELETED);
        return count > UserConstant.BOUNDARY_COUNT;
    }

    /**
     * 更新用户.
     * @param  userUpdateCommand  user update command
     * @return User
     */
    @Override
    public User updateUser(UserUpdateCommand userUpdateCommand) {
        // 根据id查找用户.
        User user = userRepository.findUserById(userUpdateCommand.getId());
        // 补全用户信息，并更新进数据库.
        return userRepository.saveUser(userFactory.buildUser(userUpdateCommand, user));
    }

    /**
     * 后台根据id查询用户.
     *
     * @param  id user id
     * @return User
     */
    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    /**
     * 后台查询所有用户.
     * @param  pageNum    page number
     * @param  pageSize   page size
     * @return user list
     */
    @Override
    public PageInfo<User> findAllUsers(int pageNum, int pageSize) {
        PageMethod.startPage(pageNum, pageSize);
        List<User> userList = userMapper.findAllUsers();
        return new PageInfo<>(userList);
    }

    /**
     * 登录逻辑分发初始化.
     */
    @PostConstruct
    public void loginDispatcherInit() {
        // 手机登录逻辑校验.
        loginDispatcher.put(AccountCategory.PHONE_TYPE.getCode(), u -> userLoginStrategyHandler(new UserMobileLoginStrategy(userRepository), u));
        // 电子邮箱登录逻辑校验.
        loginDispatcher.put(AccountCategory.EMAIL_TYPE.getCode(), u -> userLoginStrategyHandler(new UserEmailLoginStrategy(userRepository), u));
        // 用户名登录逻辑校验.
        loginDispatcher.put(AccountCategory.NAME_TYPE.getCode(), u -> userLoginStrategyHandler(new UserNameLoginStrategy(userRepository), u));
    }

    /**
     * 执行用户登录策略.
     * @param  userLoginStrategy  用户登录策略
     * @param  userLoginQuery     用户登录查询对象
     * @return Optional<Account>
     */
    public Optional<Account> userLoginStrategyHandler(UserLoginStrategy userLoginStrategy, UserLoginQuery userLoginQuery) {

        Optional<Long> uid = userLoginStrategy.findUser(userLoginQuery.getName(), userLoginQuery.getPassword());

        return uid.flatMap(u -> userLoginStrategy.findAccount(userLoginQuery.getLoginType(), u));
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
        return Optional.ofNullable(loginResult).flatMap(l -> l.apply(userLoginQuery));
    }
}
