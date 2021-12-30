package com.mst.csuserservice.domain.service.impl;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csuserservice.constant.UserConstant;
import com.mst.csuserservice.domain.mapper.PermissionMapper;
import com.mst.csuserservice.domain.mapper.RoleMapper;
import com.mst.csuserservice.domain.utils.CastUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        SaSession session = StpUtil.getSessionByLoginId(loginId);
        // 从缓存中获取权限列表.
        Object permissions = session.get(UserConstant.PERMISSIONS_KEY, () -> {
            List<String> list = permissionMapper.findPermissionsByUserId(Long.valueOf(loginId.toString()));
            session.set(UserConstant.PERMISSIONS_KEY, list);
            return list;
        });
        return new ArrayList<>(CastUtil.castList(permissions, String.class));
    }

    /**
     * 获取角色列表.
     * @param  loginId user id
     * @param  s       some args
     * @return role list
     */
    @Override
    public List<String> getRoleList(Object loginId, String s) {

        SaSession session = StpUtil.getSessionByLoginId(loginId);
        // 从缓存中获取角色列表.
        Object roles = session.get(UserConstant.ROLES_KEY, () -> {
            List<String> list = roleMapper.findRolesByUserId(Long.valueOf(loginId.toString()));
            session.set(UserConstant.ROLES_KEY, list);
            return list;
        });
        return new ArrayList<>(CastUtil.castList(roles, String.class));
    }
}
