package com.mst.csuserservice.application;

import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.application.dto.UserDTO;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.command.UserUnBindCommand;
import com.mst.csuserservice.controller.cqe.command.UserUpdateCommand;
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

    /**
     * 退出.
     * @return UserDTO
     */
    public UserDTO logout() {
        String loginId = (String) StpUtil.getLoginIdDefaultNull();
        // 注销会话.
        StpUtil.logoutByLoginId(loginId);
        // 响应退出结果.
        return UserDtoFactory.newUserDtoForLogout(loginId);
    }

    /**
     * 后台创建用户.
     * @param   userCreateCommand  create user command
     * @return  UserDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDTO createUser(UserCreateCommand userCreateCommand) {
        // 启动用户领域服务完成添加用户操作.
        User user = userService.createUser(userCreateCommand);
        // 响应创建结果.
        return UserDtoFactory.newUserDtoForRegister(user);
    }

    /**
     * 后台删除用户.
     * @param   userUnBindCommand  user unbind command
     * @return  UserDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDTO removeUser(UserUnBindCommand userUnBindCommand) {
        // 启动用户领域服务完成解除用户与角色绑定操作.
        Boolean unBindResult = userService.unBindUserAndRole(userUnBindCommand);
        // 启动用户领域服务完成禁用用户操作.
        Boolean removeResult = userService.removeUser(userUnBindCommand.getUserIdList());
        // 响应删除结果.
        return UserDtoFactory.newUserDtoForRemove(removeResult, unBindResult);
    }

    /**
     * 后台更新用户.
     * @param   userUpdateCommand  user update command
     * @return  UserDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDTO updateUser(UserUpdateCommand userUpdateCommand) {
        // 启动用户领域服务完成更新信息操作.
        User updateUser = userService.updateUser(userUpdateCommand);
        // 响应更新结果.
        return UserDtoFactory.newUserDtoForUpdate(updateUser);
    }
}
