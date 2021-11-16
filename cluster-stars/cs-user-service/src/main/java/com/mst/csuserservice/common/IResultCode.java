package com.mst.csuserservice.common;

/**
 * @author Molin
 * @date 2021-11-16 22:21
 * 提供封装状态码的接口
 */
public interface IResultCode {

    /**
     * 获取状态码.
     * @return code value
     */
    long getCode();

    /**
     * 获取响应信息.
     * @return message value
     */
    String getMessage();
}
