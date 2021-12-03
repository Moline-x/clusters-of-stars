package com.mst.csuserservice.domain.factory;

import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.command.UserUpdateCommand;
import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.model.UserRole;

/**
 * @author Molin
 * @date 2021/11/26  13:18
 * class description: 用户领域工厂接口
 */
public interface UserFactory {

    /**
     * 根据指令构建用户实体.
     * @param   userCreateCommand  create user command
     * @return  User
     */
    User buildUser(UserCreateCommand userCreateCommand);

    /**
     * 根据userId构建openCode.
     * @param  userId  user id
     * @return openCode
     */
    String buildOpenCode(Long userId);

    /**
     * 根据userId和openCode创建账户.
     * @param  openCode  登录识别码
     * @param  userId    user id
     * @return Account
     */
    Account buildAccount(String openCode, Long userId);

    /**
     * 根据userId和roleId创建账户.
     * @param  userId     user id
     * @param  roleId     role id
     * @return UserRole
     */
    UserRole buildUserRole(Long userId, Long roleId);

    /**
     * 根据指令更新实体.
     * @param  userUpdateCommand user update command
     * @param  user              user
     * @return User
     */
    User buildUser(UserUpdateCommand userUpdateCommand, User user);
}
