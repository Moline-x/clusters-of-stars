package com.mst.csproductservice.domain.exception;

/**
 * @author Molin
 * @date 2021/12/24  17:25
 * class description: 自定义无效参数异常.
 */
public class InvalidParameterException extends BusinessException{

    /**
     * 异常code.
     */
    private static final String CODE = "invalid-parameter";

    public InvalidParameterException(String message) {
        super(CODE, message);
    }
}
