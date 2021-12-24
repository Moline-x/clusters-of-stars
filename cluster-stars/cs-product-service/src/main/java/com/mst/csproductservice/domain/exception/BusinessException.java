package com.mst.csproductservice.domain.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Molin
 * @date 2021/12/24  17:19
 * class description: 通用运行时异常
 */
@Setter
@Getter
public class BusinessException extends RuntimeException{

    /**
     * 异常code.
     */
    private final String code;

    /**
     * 异常信息.
     */
    private final String message;

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
