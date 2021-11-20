package com.mst.csuserservice.application;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.mst.csuserservice.application.DTO.UserDTO;
import com.mst.csuserservice.domain.model.User;
import com.mst.csuserservice.domain.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Molin
 * @date   2021-11-19  23:15
 * 用户应用层管理
 */
@Component
public class UserManager {

    private final UserService userService;

    public UserManager(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册.
     * @param  user  user
     * @return UserDTO
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDTO register(User user) {
        UserDTO userDTO = new UserDTO();
        User register = userService.register(user);
        if (register == null) {
            userDTO.setMsg(false);
            return userDTO;
        }
        userDTO.setUser(register);
        userDTO.setMsg(true);
        return userDTO;
    }

    /**
     * 登陆.
     * @param  username  username
     * @param  password  password
     * @return UserDTO
     */
    public UserDTO login(String username, String password) {
        UserDTO userDTO = new UserDTO();
        SaTokenInfo tokenInfo = userService.login(username, password);
        if (tokenInfo == null) {
            userDTO.setMsg(false);
            return userDTO;
        }
        Map<String, String> tokenMap = new HashMap<>(6);
        tokenMap.put("token", tokenInfo.getTokenValue());
        tokenMap.put("tokenHead", tokenInfo.getTokenName());
        tokenMap.put("loginId", String.valueOf(tokenInfo.getLoginId()));
        userDTO.setTokenMap(tokenMap);
        userDTO.setMsg(true);
        return userDTO;
    }
}
