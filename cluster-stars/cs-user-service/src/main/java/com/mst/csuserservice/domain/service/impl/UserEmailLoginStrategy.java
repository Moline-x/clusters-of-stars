package com.mst.csuserservice.domain.service.impl;

import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.repository.UserRepository;
import com.mst.csuserservice.domain.service.strategy.UserLoginStrategy;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Molin
 * @date 2021/11/26  15:34
 * class description: 用户电子邮箱登录策略
 */
public class UserEmailLoginStrategy implements UserLoginStrategy {

    private final UserRepository userRepository;

    public UserEmailLoginStrategy(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 根据电子邮箱和密码查询用户.
     * @param  loginText 登录内容
     * @param  password  密码
     * @return Optional user id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Optional<Long> findUser(String loginText, String password) {
        // 根据电子邮箱和密码查询用户.
        Optional<User> userOptional = userRepository.findByEmailAndPassword(loginText, password);
        // 如果用户存在，则更新最新登录类型.
        if (userOptional.isPresent()) {
            // 获取用户实体.
            User user = userOptional.get();
            // 返回用户Id.
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
