package com.mst.csuserservice.infrastructure.factory;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.github.pagehelper.PageInfo;
import com.mst.csuserservice.application.dto.UserDTO;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.domain.bo.UserLoginBO;
import com.mst.csuserservice.domain.model.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


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
        Optional.ofNullable(user).ifPresentOrElse(u -> {
            userDTO.setMsg(true);
            userDTO.setUser(user);
        }, () -> userDTO.setMsg(false));
        return userDTO;
    }

    /**
     * 登录时创建UserDTO的封装.
     * @param  userLoginBO token information and permissions
     * @return UserDTO
     */
    public static UserDTO newUserDtoForLogin(final UserLoginBO userLoginBO) {

        UserDTO userDTO = new UserDTO();
        userLoginBO.getTokenInfo().ifPresentOrElse(t -> {
            userDTO.setMsg(true);
            userDTO.setTokenMap(createTokenMap(t));
            userDTO.setPermissions(userLoginBO.getPermissionsList());
        }, () -> userDTO.setMsg(false));
        return userDTO;
    }

    /**
     * 构建tokenMap工具.
     * @param  tokenInfo token information
     * @return token information map
     */
    private static Map<String, String> createTokenMap(final SaTokenInfo tokenInfo) {
        String userId = String.valueOf(tokenInfo.getLoginId());
        Map<String, String> tokenMap = new HashMap<>(UserConstant.TOKEN_MAP_CAPACITY);
        tokenMap.put("token", tokenInfo.getTokenValue());
        tokenMap.put("tokenHead", tokenInfo.getTokenName());
        tokenMap.put("loginId", userId);
        return tokenMap;
    }

    /**
     * 退出时创建UserDTO的封装.
     * @param  loginId  login id
     * @return UserDTO
     */
    public static UserDTO newUserDtoForLogout(final String loginId) {

        UserDTO userDTO = new UserDTO();
        Optional.ofNullable(loginId).ifPresentOrElse(l -> {
            userDTO.setMsg(true);
            userDTO.setUser(User.builder().name(loginId).build());
        }, () -> userDTO.setMsg(false));
        return userDTO;
    }

    /**
     * 删除用户时创建UserDTO的封装.
     * @param  removeResult  remove result
     * @param  unbindResult  unbind result
     * @return UserDTO
     */
    public static UserDTO newUserDtoForRemove(final Boolean removeResult, final Boolean unbindResult) {
        UserDTO userDTO = new UserDTO();
        userDTO.setMsg(removeResult && unbindResult);
        return userDTO;
    }

    /**
     * 更新用户时创建UserDTO的封装.
     * @param  user  user update info
     * @return UserDTO
     */
    public static UserDTO newUserDtoForUpdate(final User user) {

        return newUserDtoForRegister(user);
    }

    /**
     * 查询单个用户时创建UserDTO的封装.
     * @param  user user info
     * @return UserDTO
     */
    public static UserDTO newUserDtoForFindOne(final User user) {

        return newUserDtoForUpdate(user);
    }

    /**
     * 查询所有用户时创建DTO的封装.
     * @param  list user info list by page
     * @return UserDTO
     */
    public static UserDTO newUserDtoForFindAll(final PageInfo<User> list) {
        UserDTO userDTO = new UserDTO();
        if (!list.getList().isEmpty()) {
            userDTO.setUserList(list);
            userDTO.setMsg(true);
        } else {
            userDTO.setMsg(false);
        }
        return userDTO;
    }
}
