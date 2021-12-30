package com.mst.csgateway.constant.enums.roles;

/**
 * @author Molin
 * @date 2021/12/30  13:29
 * class description: 角色
 */
public enum Role {
    /**
     * 角色
     */
    SUPER_ADMIN("super-admin"),
    ADMIN("admin"),
    CUSTOMER("customer");

    private final String code;

    Role(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
