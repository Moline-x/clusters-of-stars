package com.mst.csuserservice.controller.common;

/**
 * @author Molin
 * @date 2021-11-16 22:27
 * 枚举常用状态码
 */
public enum ResultCode implements IResultCode{

    /**
     * API操作状态码.
     */
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATED_FAILED(404, "参数校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已过期"),
    FORBIDDEN(403, "没有相关权限");

    private final long code;

    private final String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
