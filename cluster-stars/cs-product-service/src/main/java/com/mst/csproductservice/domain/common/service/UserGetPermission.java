package com.mst.csproductservice.domain.common.service;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.mst.csproductservice.domain.common.utils.CastUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Molin
 * @date 2021/12/28  13:24
 * class description: 获取权限服务
 */
@Component
public class UserGetPermission implements StpInterface {

    /**
     * 获取权限列表.
     * @param  loginId  登录Id
     * @param  s        some args
     * @return permission list
     */
    @Override
    public List<String> getPermissionList(Object loginId, String s) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return CastUtil.castList(session.get("Permissions"), String.class);
    }

    /**
     * 获取角色列表.
     * @param  loginId  登录Id
     * @param  s        some args
     * @return role list
     */
    @Override
    public List<String> getRoleList(Object loginId, String s) {
        SaSession session = StpUtil.getSessionByLoginId(loginId);
        return CastUtil.castList(session.get("Roles"), String.class);
    }
}
