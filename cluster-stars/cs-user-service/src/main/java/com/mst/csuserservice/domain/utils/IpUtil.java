package com.mst.csuserservice.domain.utils;

import com.mst.csuserservice.constant.NetConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Molin
 * @date 2021/12/9  15:46
 * class description: ip相关工具类
 */
public class IpUtil {

    private IpUtil() {
    }
    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

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
            if(NetConstant.LOCAL_HOST_IP.equals(ip) || NetConstant.LOCAL_NETCONST.equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet;
                try {
                    inet = InetAddress.getLocalHost();
                    ip= inet.getHostAddress();
                } catch (UnknownHostException e) {
                    logger.error("getClientIp error: ", e);
                }
            }
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

}
