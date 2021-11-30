package com.mst.csuserservice.controller;

import com.mst.csuserservice.application.dto.UserDTO;
import com.mst.csuserservice.application.UserManager;
import com.mst.csuserservice.controller.component.ResultVO;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;

import com.mst.csuserservice.infrastructure.factory.UserResultFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Molin
 * @date   2021-11-19  23:30
 * 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    /**
     * 注册.
     * @param  userCreateCommand  create user command
     * @return ResultVO
     */
    @PostMapping("/register")
    public ResultVO<UserDTO> register(@RequestBody UserCreateCommand userCreateCommand) {
        // 启动应用层执行注册指令.
        UserDTO register = userManager.register(userCreateCommand);
        // 回调结果.
        return UserResultFactory.newResultForRegister(register);
    }

    /**
     * 登陆.
     * @param  userLoginQuery  user login query
     * @return ResultVO
     */
    @PostMapping("/login")
    public ResultVO<UserDTO> login(@RequestBody UserLoginQuery userLoginQuery) {
        // 启动应用层执行登录查询.
        UserDTO login = userManager.login(userLoginQuery);
        // 回调结果.
        return UserResultFactory.newResultForLogin(login);
    }
}
