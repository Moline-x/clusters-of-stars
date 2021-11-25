package com.mst.csuserservice.controller.cqe.command;

import com.mst.csuserservice.constant.UserConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Molin
 * @date   2021-11-20  09:31
 * 用户创建指令
 */
@Data
public class UserCreateCommand {

    /**
     * 用户名
     */
    @NotNull(message = UserConstant.NAME_NOT_NULL)
    private String name;

    /**
     * 密码
     */
    @NotNull(message = UserConstant.PWD_NOT_NULL)
    private String password;

    /**
     * 手机号
     */
    private String mobile;
}
