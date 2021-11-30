package com.mst.csuserservice.domain.enums;

import com.mst.csuserservice.constant.UserConstant;

/**
 * @author Molin
 * @date 2021/11/29  19:58
 * class description: 用户领域角色枚举
 */
public enum Role {

    /**
     * 超级管理员.
     */
    SUPERADMIN(UserConstant.ROLE_SUPER_ADMIN),
    /**
     * 管理员.
     */
    ADMIN(UserConstant.ROLE_ADMIN),
    /**
     * 普通用户.
     */
    CUSTOMER(UserConstant.ROLE_CUSTOMER);

    private Long code;

    Role(Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}
