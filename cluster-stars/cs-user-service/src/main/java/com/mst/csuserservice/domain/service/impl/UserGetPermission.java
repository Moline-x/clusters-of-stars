package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.mst.csuserservice.domain.mapper.PermissionMapper;
import com.mst.csuserservice.domain.mapper.RoleMapper;
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

    private final RoleMapper roleMapper;

    public UserGetPermission(PermissionMapper permissionMapper, RoleMapper roleMapper) {
        this.permissionMapper = permissionMapper;
        this.roleMapper = roleMapper;
    }

    /**
     * 获取权限列表.
     * @param  loginId user id
     * @param  s       some args
     * @return permission list
     */
    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        // 1. 根据角色Id获取权限列表.
        return permissionMapper.findPermissionsByUserId(Long.valueOf(loginId.toString()));
    }

    /**
     * 获取角色列表.
     * @param  loginId user id
     * @param  s       some args
     * @return role list
     */
    @Override
    public List<String> getRoleList(Object loginId, String s) {
        // 1. 根据用户id获取角色列表.
        return roleMapper.findRolesByUserId(Long.valueOf(loginId.toString()));
    }
}
