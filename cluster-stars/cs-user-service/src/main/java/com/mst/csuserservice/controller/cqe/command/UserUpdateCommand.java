package com.mst.csuserservice.controller.cqe.command;

import com.mst.csuserservice.constant.UserConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Molin
 * @date 2021/12/3  13:07
 * class description: 用户更新信息指令
 */
@Data
public class UserUpdateCommand {

    /**
     * 用户id
     */
    @NotNull(message = UserConstant.USER_ID_NOT_NULL)
    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = UserConstant.NAME_NOT_NULL)
    private String name;

    /**
     * 手机号
     */
    @NotNull(message = UserConstant.MOBILE_NOT_NULL)
    private String mobile;

    /**
     * 电子邮箱
     */
    @NotNull(message = UserConstant.EMAIL_NOT_NULL)
    private String email;

    /**
     * 角色Id
     */
    private Long roleId;
}
