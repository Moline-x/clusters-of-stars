package com.mst.csuserservice.infrastructure.factory;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.mst.csuserservice.application.DTO.UserDTO;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Molin
 * @date   2021-11-20  14:51
 * 基础设施创建UserDTO的工厂实现
 */
@Component
public class UserDtoFactory {

    private UserDtoFactory() {}

    /**
     * 注册时创建UserDTO的封装.
     * @param  user user
     * @return UserDTO
     */
    public static UserDTO newUserDtoForRegister(final User user) {
        UserDTO userDTO = new UserDTO();
        if (user == null) {
            userDTO.setMsg(false);
            return userDTO;
        }
        userDTO.setUser(user);
        userDTO.setMsg(true);
        return userDTO;
    }

    /**
     * 登录时创建UserDTO的封装.
     * @param  tokenInfo token information
     * @return UserDTO
     */
    public static UserDTO newUserDtoForLogin(final SaTokenInfo tokenInfo) {
        UserDTO userDTO = new UserDTO();
        if (tokenInfo == null) {
            userDTO.setMsg(false);
            return userDTO;
        }
        Map<String, String> tokenMap = new HashMap<>(UserConstant.TOKEN_MAP_CAPACITY);
        tokenMap.put("token", tokenInfo.getTokenValue());
        tokenMap.put("tokenHead", tokenInfo.getTokenName());
        tokenMap.put("loginId", String.valueOf(tokenInfo.getLoginId()));
        userDTO.setTokenMap(tokenMap);
        userDTO.setMsg(true);
        return userDTO;
    }
}
