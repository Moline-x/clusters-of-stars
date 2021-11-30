package com.mst.csuserservice.constant;

/**
 * @author Molin
 * @date 2021/11/26  10:00
 * class description: 用户领域账号常量.
 */
public final class AccountConstant {

    private AccountConstant() {
    }

    /**
     * 账号类别: 手机号.
     */
    public static final int PHONE_TYPE = 1;
    /**
     * 账号类别: 邮箱.
     */
    public static final int EMAIL_TYPE = 2;
    /**
     * 账号类别: 微信第三方.
     */
    public static final int WECHAT_TYPE = 3;
}
