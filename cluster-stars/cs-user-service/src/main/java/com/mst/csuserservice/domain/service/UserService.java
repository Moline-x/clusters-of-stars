package com.mst.csuserservice.domain.service;

import com.github.pagehelper.PageInfo;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.command.UserUnBindCommand;
import com.mst.csuserservice.controller.cqe.command.UserUpdateCommand;
import com.mst.csuserservice.controller.cqe.query.UserLoginQuery;
import com.mst.csuserservice.controller.cqe.query.UserQuery;
import com.mst.csuserservice.domain.bo.UserLoginBO;
import com.mst.csuserservice.domain.model.LoginLog;
import com.mst.csuserservice.domain.model.User;

import java.util.List;

/**
 * @author Molin
 * @date   2021-11-19  22:45
 * 用户领域服务接口
 */
public interface UserService extends BaseService<UserQuery, User>{

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

    /**
     * 保存登录日志.
     * @param  loginLog login log
     * @param  userId   user id
     */
    void saveLoginLog(LoginLog loginLog, Long userId);

    /**
     * 获取权限列表.
     * @param  userId user id
     * @return permission list
     */
    List<String> getPermissionList(Long userId);

    /**
     * 获取角色列表.
     * @param  userId user id
     * @return role list
     */
    List<String> getRoleList(Long userId);

    /**
     * 后台创建用户.
     * @param  userCreateCommand  create user command
     * @return User
     */
    User createUser(UserCreateCommand userCreateCommand);

    /**
     * 后台删除用户.
     * @param  userIds  user id list
     * @return true or false
     */
    Boolean removeUser(List<Long> userIds);

    /**
     * 后台解除用户与角色之间绑定.
     * @param  userUnBindCommand  user unbind command
     * @return true or false
     */
    Boolean unBindUserAndRole(UserUnBindCommand userUnBindCommand);

    /**
     * 后台更新用户.
     * @param  userUpdateCommand  user update command
     * @return User
     */
    User updateUser(UserUpdateCommand userUpdateCommand);

    /**
     * 后台根据id查询用户.
     * @param  id  user id
     * @return User
     */
    User findUserById(Long id);

    /**
     * 后台查询所有用户.
     * @param   pageNum    page number
     * @param   pageSize   page size
     * @return  user list
     */
    PageInfo<User> findAllUsers(int pageNum, int pageSize);
}
