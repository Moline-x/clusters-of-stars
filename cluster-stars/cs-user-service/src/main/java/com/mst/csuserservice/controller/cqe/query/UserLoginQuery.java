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
     * 用户名
     */
    @NotNull(message = UserConstant.NAME_NOT_NULL)
    private String name;

    /**
     * 密码
     */
    @NotNull(message = UserConstant.PWD_NOT_NULL)
    private String password;
}
