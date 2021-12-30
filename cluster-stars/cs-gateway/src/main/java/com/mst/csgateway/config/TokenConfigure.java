package com.mst.csgateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.mst.csgateway.constant.enums.permissions.Permission2User;
import com.mst.csgateway.constant.enums.roles.Role;
import com.mst.csgateway.constant.enums.urls.CommonResource;
import com.mst.csgateway.constant.enums.urls.Resource2Admin;
import com.mst.csgateway.constant.enums.urls.Resource2Product;
import com.mst.csgateway.constant.enums.urls.Resource2User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Molin
 * @date 2021/12/28  11:06
 * class description: 网关配置类——全局过滤器
 */
@Configuration
public class TokenConfigure {

    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
                // 拦截地址
                .addInclude(CommonResource.FULL.symbol())
                // 鉴权方法，每次访问进入
                .setAuth(obj -> {
                    // 登录校验，拦截所有路由
                    SaRouter.match(CommonResource.FULL.symbol(), Resource2User.LOGIN.uri(), r -> StpUtil.checkLogin());
                    // 角色认证
                    SaRouter.match(Resource2Admin.FULL_PATH.uri(), r -> StpUtil.checkRoleOr(Role.SUPER_ADMIN.code(),Role.ADMIN.code()));
                    // 权限认证，不同模块，校验不同权限
                    SaRouter.match(Resource2Admin.FULL_PATH.uri()).free(r -> {
                        SaRouter.match(Resource2Admin.BUILD.uri(), route -> StpUtil.checkPermission(Permission2User.CREATE.code()));
                        SaRouter.match(Resource2Admin.REMOVE.uri(), route -> StpUtil.checkPermission(Permission2User.DELETE.code()));
                        SaRouter.match(Resource2Admin.UPDATE.uri(), route -> StpUtil.checkPermission(Permission2User.UPDATE.code()));
                        SaRouter.match(Resource2Admin.GET.uri(), route -> StpUtil.checkPermission(Permission2User.GET.code()));
                    });
                    SaRouter.match(Resource2Product.FULL_PATH.uri()).free(r -> {
                       SaRouter.match(Resource2Product.TEST_CONNECTION.uri(), route -> StpUtil.checkPermission(Permission2User.CREATE.code()));
                    });
                })
                .setError(e -> SaResult.error(e.getMessage()));
    }
}
