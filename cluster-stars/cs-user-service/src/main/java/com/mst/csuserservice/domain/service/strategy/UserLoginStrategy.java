package com.mst.csuserservice.domain.service.strategy;

import com.mst.csuserservice.domain.model.Account;

import java.util.Optional;

/**
 * @author Molin
 * @date 2021/11/26  14:18
 * class description: 用户领域登录策略
 */
public interface UserLoginStrategy {

    /**
     * 根据登录内容和密码执行登录策略.
     * @param  loginText 登录内容
     * @param  password  密码
     * @return Optional user id
     */
    Optional<Long> findUser(String loginText, String password);

    /**
     * 根据用户Id查找账户.
     * @param  loginType 登录类型
     * @param  userId    用户ID
     * @return optional account
     */
    Optional<Account> findAccount(int loginType, Long userId);
}
