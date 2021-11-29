package com.mst.csuserservice.domain.service;

import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.domain.bo.UserLoginBO;
import com.mst.csuserservice.domain.model.User;

/**
 * @author Molin
 * @date   2021-11-19  22:45
 * 用户领域服务接口
 */
public interface UserService {

    /**
     * 注册.
     * @param   userCreateCommand  create user command
     * @return  User
     */
    User register(UserCreateCommand userCreateCommand);

    /**
     * 登录.
     * @param   userLoginQuery  login user query
     * @return  SaTokenInfo
     */
    UserLoginBO login(UserLoginQuery userLoginQuery);
}
