package com.mst.csgateway.config;

import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
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
                .addInclude("/**")
                // 鉴权方法，每次访问进入
                .setAuth(obj -> {
                    // 登录校验，拦截所有路由
                    SaRouter.match("/**", "/user/user/login", r -> StpUtil.checkLogin());
                })
                .setError(e -> SaResult.error(e.getMessage()));
    }
}
