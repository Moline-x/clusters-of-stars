package com.mst.csuserservice.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.application.dto.UserDTO;
import com.mst.csuserservice.application.UserManager;
import com.mst.csuserservice.controller.component.ResultVO;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;

import com.mst.csuserservice.infrastructure.factory.UserResultFactory;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 退出.
     * @return ResultVO
     */
    @GetMapping("/logout")
    public ResultVO<UserDTO> logout() {
        // 启动应用层执行会话退出.
        UserDTO logout = userManager.logout();
        // 回调结果.
        return UserResultFactory.newResultForLogout(logout);
    }

    /**
     * 测试是否登录.
     * @return ResultVO<String>
     */
    @GetMapping("/isLogin")
    public String isLogIn() {
        if (StpUtil.isLogin()) {
            return "online";
        }
        return "offline";
    }

    /**
     * 后台新建一个用户.
     * @param  userCreateCommand  create user command
     * @return ResultVO<UserDTO>
     */
    @SaCheckPermission("user-add")
    @PostMapping("/create")
    public ResultVO<UserDTO> createUser(@RequestBody UserCreateCommand userCreateCommand) {
        // 启动应用层构建用户指令.
        UserDTO addUser = userManager.createUser(userCreateCommand);
        // 回调结果.
        return UserResultFactory.newResultForCreateUser(addUser);
    }

    /**
     * 后台删除一个用户.
     * @param  userId  user id
     * @return ResultVO<UserDTO>
     */
//    @SaCheckPermission("user-delete")
//    @GetMapping("/remove")
//    public ResultVO<UserDTO> removeUser(@RequestParam Long userId) {
//        // 根据user id启动应用层删除用户指令
//        // 回调结果.
//    }
}
