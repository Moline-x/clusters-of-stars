package com.mst.csuserservice.constant;

/**
 * @author Molin
 * @date   2021-11-19  22:10
 * 用户领域常量
 */
public final class UserConstant {

    private UserConstant() {
    }

    /**
     * 密码加盐.
     */
    public static final String PWD_SALT = "cluster-stars";
    /**
     * 注册成功提示语.
     */
    public static final String REGISTER_SUCCEED = "注册成功啦!";
    /**
     * 注册失败提示语.
     */
    public static final String REGISTER_FAILED = "注册失败⊙﹏⊙";
    /**
     * 用户名或密码错误提示语.
     */
    public static final String WRONG_NAME_PWD = "用户名或密码错误!";
    /**
     * 登陆成功提示语.
     */
    public static final String LOGIN_SUCCEED = "登陆成功，欢迎回来!";
    /**
     * 参数校验——用户名.
     */
    public static final String NAME_NOT_NULL = "用户名不能为空!";
    /**
     * 参数校验——密码.
     */
    public static final String PWD_NOT_NULL = "密码不能为空!";
    /**
     * 参数校验——手机号.
     */
    public static final String MOBILE_NOT_NULL = "手机号不能为空";
    /**
     * 参数校验——电子邮箱.
     */
    public static final String EMAIL_NOT_NULL = "电子邮箱不能为空";
    /**
     * 用户状态常量——启用.
     */
    public static final int ENABLE_CODE = 1;
    /**
     * 用户状态常量——禁用.
     */
    public static final int DISABLE_CODE = 0;
    /**
     * tokenMap初始化容量.
     */
    public static final int TOKEN_MAP_CAPACITY = 6;
    /**
     * 超级管理员角色ID.
     */
    public static final Long ROLE_SUPER_ADMIN = 1L;
    /**
     * 普通管理员角色ID.
     */
    public static final Long ROLE_ADMIN = 2L;
    /**
     * 普通用户角色ID.
     */
    public static final Long ROLE_CUSTOMER = 3L;
}
