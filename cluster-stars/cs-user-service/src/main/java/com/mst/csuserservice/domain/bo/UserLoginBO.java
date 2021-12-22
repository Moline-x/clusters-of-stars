package com.mst.csuserservice.domain.bo;

import cn.dev33.satoken.stp.SaTokenInfo;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Optional;

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
    private Optional<SaTokenInfo> tokenInfo;

    /**
     * 登录识别码.
     */
    private Optional<Long> loginId;

    /**
     * 权限列表.
     */
    private List<String> permissionsList;

    /**
     * 角色列表.
     */
    private List<String> rolesList;
}
