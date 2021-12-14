package com.mst.csuserservice.domain.enums;

import com.mst.csuserservice.constant.UserConstant;

/**
 * @author Molin
 * @date   2021-11-19 22:07
 * 用户状态枚举.
 */
public enum UserState {
    /**
     * 禁用.
     */
    DISABLED(UserConstant.DISABLE_CODE),
    /**
     * 启用.
     */
    ENABLED(UserConstant.ENABLE_CODE);

    /**
     * 状态值.
     */
    private final int code;

    UserState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
