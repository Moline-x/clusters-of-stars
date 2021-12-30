package com.mst.csgateway.service;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csgateway.constant.AuthConstant;
import com.mst.csgateway.mapper.PermissionMapper;
import com.mst.csgateway.mapper.RoleMapper;
import com.mst.csgateway.utils.CastUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Molin
 * @date 2021/12/28  10:43
 * class description: 网关服务层——获取当前用户权限与角色列表
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
     * @param  loginId login id
     * @param  s       some args
     * @return permission list
     */
    @Override
    public List<String> getPermissionList(Object loginId, String s) {

        SaSession session = StpUtil.getSessionByLoginId(loginId);
        Object permissions = session.get(AuthConstant.PERMISSIONS_KEY, () -> {
            List<String> list = permissionMapper.findPermissionsByUserId(Long.valueOf(loginId.toString()));
            session.set(AuthConstant.PERMISSIONS_KEY, list);
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
        Object roles = session.get(AuthConstant.ROLES_KEY, () -> {
            List<String> list = roleMapper.findRolesByUserId(Long.valueOf(loginId.toString()));
            session.set(AuthConstant.ROLES_KEY, list);
            return list;
        });
        return new ArrayList<>(CastUtil.castList(roles, String.class));
    }
}
