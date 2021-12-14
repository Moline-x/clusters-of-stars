package com.mst.csuserservice.constant;

/**
 * @author Molin
 * @date 2021/12/9  15:52
 * class description: 请求头相关常量
 */
public class NetConstant {

    private NetConstant() {
    }

    /**
     * 未知IP地址标识.
     */
    public static final String UNKNOWN_IP = "unknown";

    /**
     * 默认获取请求头.
     */
    public static final String X_FORWARD_FOR = "x-forwarded-for";

    /**
     * 真实IP请求头.
     */
    public static final String X_REAL_IP = "X-Real-IP";

    /**
     * 客户端IP请求头.
     */
    public static final String CLIENT_IP = "http_client_ip";

    /**
     * 代理请求头.
     */
    public static final String PROXY_CLIENT_IP = "Proxy-Client-IP";

    /**
     * WL代理请求头.
     */
    public static final String WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";

    /**
     * http x-forwarded-for请求头.
     */
    public static final String HTTP_X_FORWARD_FOR = "HTTP_X_FORWARDED_FOR";

    /**
     * 多个ip分隔符.
     */
    public static final String SPLIT_SYMBOL = ",";
}
