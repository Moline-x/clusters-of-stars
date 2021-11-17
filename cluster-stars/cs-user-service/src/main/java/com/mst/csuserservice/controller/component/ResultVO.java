package com.mst.csuserservice.controller.component;

import com.mst.csuserservice.controller.common.IResultCode;
import com.mst.csuserservice.controller.common.ResultCode;
import lombok.Getter;

/**
 * @author Molin
 * @date 2021-11-16 22:53
 * 通用返回对象实现
 */
@Getter
public class ResultVO<T> {

    /**
     * 响应状态码.
     */
    private long code;
    /**
     * 响应信息.
     */
    private String message;
    /**
     * 响应数据载荷.
     */
    private T data;

    public ResultVO(T data){
    }

    public ResultVO(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果.
     * @param data 数据载荷
     * @return     ResultVO
     */
    public static <T> ResultVO<T> success(T data) {
        return new ResultVO<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果.
     * @param data    数据载荷
     * @param message 提示信息
     * @return        ResultVO
     */
    public static <T> ResultVO<T> success(T data, String message) {
        return new ResultVO<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果.
     * @param errorCode  失败状态码
     * @return           ResultVO
     */
    public static <T> ResultVO<T> failed(IResultCode errorCode) {
        return new ResultVO<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果.
     * @param message 提示信息
     * @return        ResultVO
     */
    public static <T> ResultVO<T> failed(String message) {
        return new ResultVO<>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果.
     * @return        ResultVO
     */
    public static <T> ResultVO<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果.
     * @return        ResultVO
     */
    public static <T> ResultVO<T> validateFailed() {
        return failed(ResultCode.VALIDATED_FAILED);
    }

    /**
     * 参数验证失败返回结果.
     * @param message 提示信息
     * @return        ResultVO
     */
    public static <T> ResultVO<T> validateFailed(String message) {
        return new ResultVO<>(ResultCode.VALIDATED_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果.
     * @param data    数据载荷
     * @return        ResultVO
     */
    public static <T> ResultVO<T> unauthorized(T data) {
        return new ResultVO<>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未登录返回结果.
     * @param message 提示信息
     * @return        ResultVO
     */
    public static <T> ResultVO<T> unauthorized(String message) {
        return new ResultVO<>(ResultCode.UNAUTHORIZED.getCode(), message, null);
    }

    /**
     * 未授权返回结果.
     * @param data    数据载荷
     * @return        ResultVO
     */
    public static <T> ResultVO<T> forbidden(T data) {
        return new ResultVO<>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    /**
     * 未授权返回结果.
     * @param message 提示信息
     * @return        ResultVO
     */
    public static <T> ResultVO<T> forbidden(String message) {
        return new ResultVO<>(ResultCode.FORBIDDEN.getCode(), message, null);
    }
}
