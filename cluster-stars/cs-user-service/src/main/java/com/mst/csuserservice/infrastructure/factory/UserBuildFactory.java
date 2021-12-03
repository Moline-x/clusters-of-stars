package com.mst.csuserservice.infrastructure.factory;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.mst.csuserservice.constant.AccountConstant;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.controller.cqe.command.UserUpdateCommand;
import com.mst.csuserservice.domain.enums.UserState;
import com.mst.csuserservice.domain.factory.UserFactory;
import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.model.UserRole;
import org.springframework.stereotype.Component;

/**
 * @author Molin
 * @date 2021/11/26  13:21
 * class description: 基础设施用户构建工厂实现
 */
@Component
public class UserBuildFactory implements UserFactory {

    /**
     * 根据指令构建用户实体.
     * @param   userCreateCommand  create user command
     * @return  User
     */
    @Override
    public User buildUser(UserCreateCommand userCreateCommand) {
        return User.builder().name(userCreateCommand.getName())
                .mobile(userCreateCommand.getMobile())
                .email(userCreateCommand.getEmail())
                .salt(UserConstant.PWD_SALT)
                .password(SaSecureUtil.md5BySalt(userCreateCommand.getPassword(), UserConstant.PWD_SALT))
                .state(UserState.ENABLED).build();
    }

    /**
     * 根据用户id构建OpenCode.
     * @param  userId  user id
     * @return openCode
     */
    @Override
    public String buildOpenCode(Long userId) {
        return SaSecureUtil.md5(String.valueOf(userId));
    }

    /**
     * 根据userId和openCode创建账户.
     * @param  openCode  登录识别码
     * @param  userId    user id
     * @return Account
     */
    @Override
    public Account buildAccount(String openCode, Long userId) {
        return Account.builder().userId(userId)
                .openCode(openCode)
                .category(AccountConstant.PHONE_TYPE).build();
    }

    /**
     * 根据userId和role id构建用户角色关系.
     * @param  userId     user id
     * @param  roleId     role id
     * @return UserRole
     */
    @Override
    public UserRole buildUserRole(Long userId, Long roleId) {
        return UserRole.builder().userId(userId).roleId(roleId).build();
    }

    /**
     * 根据指令更新用户实体.
     *
     * @param userUpdateCommand user update command
     * @param user              user
     * @return User
     */
    @Override
    public User buildUser(UserUpdateCommand userUpdateCommand, User user) {

        return User.builder().id(userUpdateCommand.getId())
                .name(userUpdateCommand.getName())
                .password(user.getPassword())
                .salt(user.getSalt())
                .state(user.getState())
                .created(user.getCreated())
                .mobile(userUpdateCommand.getMobile())
                .email(userUpdateCommand.getEmail())
                .build();
    }
}
