package com.mst.csuserservice.domain.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.mst.csuserservice.domain.model.User;

/**
 * @author Molin
 * @date   2021-11-19  22:45
 * 用户领域服务接口
 */
public interface UserService {

    /**
     * 注册.
     * @param   user  user
     * @return  User
     */
    User register(User user);

    /**
     * 登录.
     * @param   username  username
     * @param   password  password
     * @return  SaTokenInfo
     */
    SaTokenInfo login(String username, String password);
}
