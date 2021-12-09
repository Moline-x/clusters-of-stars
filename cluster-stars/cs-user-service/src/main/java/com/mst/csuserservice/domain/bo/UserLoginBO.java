package com.mst.csuserservice.domain.bo;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.mst.csuserservice.domain.model.LoginLog;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Molin
 * @date 2021/11/29  22:18
 * class description: 用户登录业务对象
 */
@Data
@Builder
@ToString
public class UserLoginBO {

    /**
     * token info.
     */
    private SaTokenInfo tokenInfo;

    /**
     * 登录识别码.
     */
    private String openCode;

    /**
     * 权限列表.
     */
    private List<String> permissionsList;

    /**
     * 角色列表.
     */
    private List<String> rolesList;

    /**
     * 登录日志.
     */
    private LoginLog loginLog;
}
