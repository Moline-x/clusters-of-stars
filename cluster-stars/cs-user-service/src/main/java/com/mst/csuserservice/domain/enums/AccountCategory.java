package com.mst.csuserservice.domain.enums;

import com.mst.csuserservice.constant.AccountConstant;

/**
 * @author Molin
 * @date 2021/11/26  9:59
 * class description: 账号类型枚举.
 */
public enum AccountCategory {

    /**
     * 手机号.
     */
    PHONE_TYPE(AccountConstant.PHONE_TYPE),
    /**
     * 邮箱.
     */
    EMAIL_TYPE(AccountConstant.EMAIL_TYPE),
    /**
     * 微信.
     */
    WECHAT_TYPE(AccountConstant.WECHAT_TYPE);

    /**
     * 状态码.
     */
    private final int code;

    AccountCategory(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
