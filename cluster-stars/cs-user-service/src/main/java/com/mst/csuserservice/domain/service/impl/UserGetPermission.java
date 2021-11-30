package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.mst.csuserservice.domain.mapper.PermissionMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Molin
 * @date 2021/11/29  20:57
 * class description: 用户领域获取权限列表与角色列表.
 */
@Component
public class UserGetPermission implements StpInterface {

    private final PermissionMapper permissionMapper;

    public UserGetPermission(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    /**
     *
     * @param  loginId user id
     * @param  s       some args
     * @return permission list
     */
    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        // 1. 根据角色Id获取权限列表.
        return permissionMapper.findPermissionsByUserId((Long) loginId);
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
