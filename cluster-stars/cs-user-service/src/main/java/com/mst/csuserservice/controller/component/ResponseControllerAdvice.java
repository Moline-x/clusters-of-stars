package com.mst.csuserservice.controller.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Molin
 * @date   2021-11-17  23:45
 * 统一返回对象前置增强
 */
@RestControllerAdvice(basePackages = {"com.mst.csuserservice.controller"})
public class ResponseControllerAdvice implements ResponseBodyAdvice<Object> {

    /**
     * 支持增强判断方法
     * @param  returnType   方法的返回值类型
     * @param  convertType  待转换类型
     * @return boolean      true：不执行beforeBodyWrite方法, false：执行beforeBodyWrite方法
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> convertType) {
        return returnType.getGenericParameterType().equals(ResultVO.class);
    }

    /**
     * 增强方法
     * @param   data                  容器内部数据
     * @param   returnType            返回值类型
     * @param   selectedContentType   可选择的内容类型
     * @param   selectedConvertType   可选择的转换类型
     * @param   request               Servlet请求
     * @param   response              Servlet响应
     * @return  Object                利用继承多态特性，返回公共父类
     */
    @Override
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConvertType, ServerHttpRequest request, ServerHttpResponse response) {
        // 字符串类型需要特殊处理
        if (returnType.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new ResultVO<>(data));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return new ResultVO<>(data);
    }
}
