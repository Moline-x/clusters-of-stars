package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.constant.UserConstant;
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
     * @param   user  user
     * @return  User
     */
    @Override
    public User register(User user) {
        user.setSalt(UserConstant.PWD_SALT);
        user.setPassword(SaSecureUtil.md5BySalt(user.getPassword(), user.getSalt()));
        user.setState(UserState.ENABLED);
        return userRepository.saveUser(user);
    }

    /**
     * 登录.
     * @param   username  username
     * @param   password  password
     * @return  SaTokenInfo
     */
    @Override
    public SaTokenInfo login(String username, String password) {
        SaTokenInfo saTokenInfo = null;
        // 当前密码加密
        String secretKey = SaSecureUtil.md5BySalt(password, UserConstant.PWD_SALT);
        // 根据用户名和密码查找用户.
        Optional<User> userOption = userRepository.findByNameAndPassword(username, secretKey);
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
