package com.mst.csuserservice.controller.cqe.query;

import com.mst.csuserservice.constant.UserConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author Molin
 * @date   2021-11-20  09:28
 * 用户登录用查询对象
 */
@Data
public class UserLoginQuery {

    /**
     * 密码
     */
    @NotNull(message = UserConstant.PWD_NOT_NULL)
    private String password;

    /**
     * 手机号
     */
    @NotNull(message = UserConstant.MOBILE_NOT_NULL)
    private String name;

    /**
     * 登录类型
     */
    private int loginType;
}
