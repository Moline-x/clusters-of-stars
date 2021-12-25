package com.mst.csuserservice.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.mst.csuserservice.application.SysUserManager;
import com.mst.csuserservice.application.UserManager;
import com.mst.csuserservice.application.dto.UserDTO;
import com.mst.csuserservice.controller.component.ResultVO;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.command.UserUnBindCommand;
import com.mst.csuserservice.controller.cqe.command.UserUpdateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.controller.cqe.query.UserQuery;
import com.mst.csuserservice.domain.model.PageParam;
import com.mst.csuserservice.infrastructure.factory.UserResultFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Molin
 * @date 2021/12/7  9:21
 * class description: 后台用户接口
 */
@RestController
@RequestMapping("/sys_user")
public class SysUserController {

    private final SysUserManager sysUserManager;

    private final UserManager userManager;

    public SysUserController(SysUserManager sysUserManager, UserManager userManager) {
        this.sysUserManager = sysUserManager;
        this.userManager = userManager;
    }

    /**
     * 后台登录.
     * @param  userLoginQuery login query user
     * @return ResultVO<UserDTO>
     */
    @PostMapping("/login")
    public ResultVO<UserDTO> login(HttpServletRequest request, @RequestBody UserLoginQuery userLoginQuery) {
        // 启动应用层登录指令.
        UserDTO login = userManager.login(request, userLoginQuery);
        // 回调结果.
        return UserResultFactory.newResultForLogin(login);
    }

    /**
     * 后台新建一个用户.
     * @param  userCreateCommand  create user command
     * @return ResultVO<UserDTO>
     */
    @SaCheckPermission(value = "user-add", orRole = {"super-admin","admin"})
    @PostMapping("/create")
    public ResultVO<UserDTO> createUser(@RequestBody UserCreateCommand userCreateCommand) {
        // 启动应用层构建用户指令.
        UserDTO addUser = sysUserManager.createUser(userCreateCommand);
        // 回调结果.
        return UserResultFactory.newResultForCreateUser(addUser);
    }

    /**
     * 后台删除一个用户.
     * @param  userUnBindCommand  user unBind command
     * @return ResultVO<UserDTO>
     */
    @SaCheckPermission(value = "user-delete", orRole = {"super-admin","admin"})
    @PostMapping("/remove")
    public ResultVO<UserDTO> removeUser(@RequestBody UserUnBindCommand userUnBindCommand) {
        // 启动应用层删除用户指令.
        UserDTO removeUser = sysUserManager.removeUser(userUnBindCommand);
        // 回调结果.
        return UserResultFactory.newResultForRemoveUser(removeUser);
    }

    /**
     * 后台更新用户.
     * @param  userUpdateCommand  user update command
     * @return ResultVO<UserDTO>
     */
    @SaCheckPermission(value = "user-update", orRole = {"super-admin","admin"})
    @PostMapping("/update")
    public ResultVO<UserDTO> updateUser(@RequestBody UserUpdateCommand userUpdateCommand) {
        // 启动应用层更新用户指令.
        UserDTO updateUser = sysUserManager.updateUser(userUpdateCommand);
        // 回调结果.
        return UserResultFactory.newResultForUpdateUser(updateUser);
    }

    /**
     * 后台根据id获取用户信息.
     * @param  id user id
     * @return ResultVO<UserDTO>
     */
    @SaCheckPermission(value = "user-get", orRole = {"super-admin","admin"})
    @GetMapping("/get/{id}")
    public ResultVO<UserDTO> getOneUser(@PathVariable("id") Long id) {
        // 启动应用层获取单个用户信息.
        UserDTO userById = sysUserManager.findUserById(id);
        // 回调结果.
        return UserResultFactory.newResultForFindUserById(userById);
    }

    /**
     * 后台查询所有用户信息.
     * @param  userQuery user query parameter
     * @return ResultVO<UserDTO>
     */
    @SaCheckPermission(value = "user-get", orRole = {"super-admin","admin"})
    @PostMapping("/getPage")
    public ResultVO<UserDTO> findAllUsersByPage(@RequestBody PageParam<UserQuery> userQuery) {
        // 启动应用层获取全部用户信息.
        UserDTO allUsers = sysUserManager.findAllUsersByPage(userQuery);
        // 回调结果.
        return UserResultFactory.newResultForFindAllUser(allUsers);
    }
}
