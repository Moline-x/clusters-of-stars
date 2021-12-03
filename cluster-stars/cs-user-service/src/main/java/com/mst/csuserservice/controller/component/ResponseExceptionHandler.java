package com.mst.csuserservice.controller.component;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Molin
 * @date 2021/12/1  21:04
 * class description: 通用全局异常处理
 */
@RestControllerAdvice
public class ResponseExceptionHandler {

    /**
     * 处理无权限异常.
     * @param  ex NotPermissionException
     * @return message
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotPermissionException.class)
    public ResultVO<String> handlerNotPermissionException(NotPermissionException ex) {
        return ResultVO.forbidden(ex.getMessage());
    }

    /**
     * 处理未登录异常.
     * @param  ex NotLoginException
     * @return message
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NotLoginException.class)
    public ResultVO<String> handlerNotLoginException(NotLoginException ex) {
        return ResultVO.unauthorized(ex.getMessage());
    }
}
