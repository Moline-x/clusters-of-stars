package com.mst.csuserservice.application;

import com.mst.csuserservice.application.DTO.UserDTO;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.domain.bo.UserLoginBO;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.service.UserService;
import com.mst.csuserservice.infrastructure.factory.UserDtoFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Molin
 * @date   2021-11-19  23:15
 * 用户应用层管理
 */
@Component
public class UserManager {

    private final UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册.
     * @param  userCreateCommand  create user command
     * @return UserDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDTO register(UserCreateCommand userCreateCommand) {
        // 启动用户领域服务完成注册.
        User register = userService.register(userCreateCommand);
        // 根据注册结果响应.
        return UserDtoFactory.newUserDtoForRegister(register);
    }

    /**
     * 登陆.
     * @param  userLoginQuery  login query user
     * @return UserDTO
     */
    public UserDTO login(UserLoginQuery userLoginQuery) {
        // 启动用户领域服务完成登录.
        UserLoginBO userLoginBO = userService.login(userLoginQuery);
        // 响应登录结果.
        return UserDtoFactory.newUserDtoForLogin(userLoginBO);
    }
}
