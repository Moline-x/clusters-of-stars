package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.domain.enums.UserState;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Molin
 * @date   2021-11-19  22:55
 * 用户领域服务
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 注册.
     * @param   userCreateCommand  create user command
     * @return  User
     */
    @Override
    public User register(UserCreateCommand userCreateCommand) {
        User user = User.builder().name(userCreateCommand.getName())
                .mobile(userCreateCommand.getMobile())
                .salt(UserConstant.PWD_SALT)
                .password(SaSecureUtil.md5BySalt(userCreateCommand.getPassword(), UserConstant.PWD_SALT))
                .state(UserState.ENABLED).build();
        return userRepository.saveUser(user);
    }

    /**
     * 登录.
     * @param   userLoginQuery  login user query
     * @return  SaTokenInfo
     */
    @Override
    public SaTokenInfo login(UserLoginQuery userLoginQuery) {
        SaTokenInfo saTokenInfo = null;
        // 当前密码加密
        String secretKey = SaSecureUtil.md5BySalt(userLoginQuery.getPassword(), UserConstant.PWD_SALT);
        // 根据用户名和密码查找用户.
        Optional<User> userOption = userRepository.findByNameAndPassword(userLoginQuery.getName(), secretKey);
        // 如果用户存在
        if (userOption.isPresent()) {
            // 获取当前用户
            User user = userOption.get();
            // 执行登录
            StpUtil.login(user.getId());
            // 获取当前登录用户的token info
            saTokenInfo = StpUtil.getTokenInfo();
        }
        return saTokenInfo;
    }
}
