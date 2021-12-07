package com.mst.csuserservice.application;

import com.github.pagehelper.PageInfo;
import com.mst.csuserservice.application.dto.UserDTO;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.command.UserUnBindCommand;
import com.mst.csuserservice.controller.cqe.command.UserUpdateCommand;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.service.UserService;
import com.mst.csuserservice.infrastructure.factory.UserDtoFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Molin
 * @date 2021/12/7  9:48
 * class description: 后台用户应用层管理
 */
@Component
public class SysUserManager {

    private final UserService userService;

    public SysUserManager(UserService userService) {
        this.userService = userService;
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

    /**
     * 后台根据id查找用户.
     * @param  id  user id
     * @return UserDTO
     */
    public UserDTO findUserById(Long id) {
        // 启动用户领域服务完成查询单个用户记录操作.
        User user = userService.findUserById(id);
        // 响应查询结果.
        return UserDtoFactory.newUserDtoForFindOne(user);
    }

    /**
     * 后台查询所有用户.
     * @param  pageNum   page number
     * @param  pageSize  page size
     * @return UserDTO
     */
    public UserDTO findAllUsers(int pageNum, int pageSize) {
        // 启动用户领域服务完成查询所有用户记录操作.
        PageInfo<User> userList = userService.findAllUsers(pageNum, pageSize);
        // 响应查询结果.
        return UserDtoFactory.newUserDtoForFindAll(userList);
    }
}
