package com.mst.csuserservice.domain.utils;

import com.mst.csuserservice.constant.NetConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Molin
 * @date 2021/12/9  15:46
 * class description: ip相关工具类
 */
public class IpUtil {

    private IpUtil() {
    }

    /**
     * 根据请求获取IP地址.
     * @param  request http request
     * @return IP address string
     */
    public static String getIpAddress(HttpServletRequest request) {

        String ip = request.getHeader(NetConstant.X_FORWARD_FOR);

        if (ip == null || ip.length() == 0 || NetConstant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader(NetConstant.X_REAL_IP);
        }
        if (ip == null || ip.length() == 0 || NetConstant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader(NetConstant.CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || NetConstant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip == null || ip.length() == 0 || NetConstant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader(NetConstant.PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || NetConstant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader(NetConstant.WL_PROXY_CLIENT_IP);
        }
        if (ip == null || ip.length() == 0 || NetConstant.UNKNOWN_IP.equalsIgnoreCase(ip)) {
            ip = request.getHeader(NetConstant.HTTP_X_FORWARD_FOR);
        }
        if (ip != null && ip.contains(NetConstant.SPLIT_SYMBOL)) {
            ip = ip.substring(ip.lastIndexOf(",") + 1).trim();
        }
        return ip;
    }

    /**
     * ip 转 int型存储.
     * @param  ip ip string
     * @return ip integer
     */
    public static int ipToInteger(String ip){
        String[] ips = ip.split("\\.");
        int ipFour = 0;
        //因为每个位置最大255，刚好在2进制里表示8位
        for(String ip4: ips){
            int ip4a = Integer.parseInt(ip4);
            //这里应该用+也可以,但是位运算更快
            ipFour = (ipFour << 8) | ip4a;
        }
        return ipFour;
    }

    /**
     * 整型ip解码.
     * @param  ip ip integer
     * @return ip string
     */
    public static String integerToIp(Integer ip){
        //思路很简单，每8位拿一次，就是对应位的IP
        StringBuilder sb = new StringBuilder();
        for(int i = 3; i >= 0; i--){
            int ipa = (ip >> (8 * i)) & (0xff);
            sb.append(ipa).append(".");
        }
        sb.delete(sb.length() - 1, sb.length());
        return sb.toString();
    }

}
