package com.mst.csgateway.constant.enums.permissions;

/**
 * @author Molin
 * @date 2021/12/30  13:36
 * class description: 用户权限
 */
public enum Permission2User {

    /**
     * 权限列表.
     */
    CREATE("user-add"),
    DELETE("user-delete"),
    UPDATE("user-update"),
    GET("user-get");

    private final String code;

    Permission2User(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
