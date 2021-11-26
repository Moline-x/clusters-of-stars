package com.mst.csuserservice.domain.service.impl;

import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.domain.service.strategy.UserLoginStrategy;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Molin
 * @date 2021/11/26  14:24
 * class description: 用户手机登录策略
 */
public class UserMobileLoginStrategy implements UserLoginStrategy {

    private final UserRepository userRepository;

    public UserMobileLoginStrategy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 根据手机号和密码查询用户
     * @param  loginText 登录内容
     * @param  password  密码
     * @return Optional user id
     */
    @Override
    public Optional<Long> findUser(String loginText, String password) {
        // 根据手机号和密码查找用户.
        Optional<User> userOptional = userRepository.findByMobileAndPassword(loginText, password);
        // 如果查到了，则将当前loginType更新到Account表.
        if (userOptional.isPresent()) {
            // 获取当前用户实体
            User user = userOptional.get();
            // 返回用户id
            return Optional.of(user.getId());
        }
        return Optional.empty();
    }

    /**
     * 根据登录类型和用户id查询账户.
     * @param  loginType 登录类型
     * @param  userId    用户ID
     * @return Optional account
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<Account> findAccount(int loginType, Long userId) {
        // 根据userId查找账户.
        Optional<Account> accountOptional = userRepository.findByUserId(userId);
        // 如果查到了,则将当前loginType更新到Account表.
        if (accountOptional.isPresent()) {
            // 更新登录类型
            userRepository.updateLoginType(userId, loginType);
            // 返回账户.
            return accountOptional;
        }
        return Optional.empty();
    }
}
