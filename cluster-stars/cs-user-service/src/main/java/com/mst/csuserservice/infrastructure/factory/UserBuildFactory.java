package com.mst.csuserservice.infrastructure.factory;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.mst.csuserservice.constant.AccountConstant;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.controller.cqe.command.UserCreateCommand;
import com.mst.csuserservice.domain.enums.AccountCategory;
import com.mst.csuserservice.domain.enums.UserState;
import com.mst.csuserservice.domain.factory.UserFactory;
import com.mst.csuserservice.domain.model.Account;
import com.mst.csuserservice.domain.model.User;
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
}
