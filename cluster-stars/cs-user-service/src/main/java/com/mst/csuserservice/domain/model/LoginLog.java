package com.mst.csuserservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Molin
 * @date 2021/12/9  15:16
 * class description: 用户领域登录日志实体
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginLog {

    /**
     * 日志id.
     */
    private Long loginId;
    /**
     * 用户id.
     */
    private Long userId;
    /**
     * 登录时间.
     */
    private Date loginTime;
    /**
     * 登录ip.
     */
    private Integer loginIp;
    /**
     * 客户端类型.
     */
    private String clientType;
    /**
     * 操作系统类型.
     */
    private String osType;
    /**
     * 浏览器类型.
     */
    private String browserType;
    /**
     * 登录地址.
     */
    private String loginAddress;
    /**
     * 登录状态.
     */
    private Integer loginType;
}
