package com.mst.csuserservice.application;

import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.application.dto.UserDTO;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.domain.bo.UserLoginBO;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.service.UserService;
import com.mst.csuserservice.infrastructure.factory.UserBuildFactory;
import com.mst.csuserservice.infrastructure.factory.UserDtoFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

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
    @Transactional(rollbackFor = Exception.class)
    public UserDTO login(HttpServletRequest request, UserLoginQuery userLoginQuery) {

        // 启动用户领域服务完成登录.
        UserLoginBO userLoginBO = userService.login(userLoginQuery);
        // 封装日志.
        userLoginBO.getLoginId().ifPresent(id -> {
            // 获取权限与角色列表.
            userLoginBO.setPermissionsList(userService.getPermissionList(id));
            userLoginBO.setRolesList(userService.getRoleList(id));
            userService.saveLoginLog(new UserBuildFactory().buildLoginLog(request), id);
        });
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
        StpUtil.logout(loginId);
        // 响应退出结果.
        return UserDtoFactory.newUserDtoForLogout(loginId);
    }
}
