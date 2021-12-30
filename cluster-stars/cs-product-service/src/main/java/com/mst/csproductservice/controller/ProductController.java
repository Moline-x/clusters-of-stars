package com.mst.csproductservice.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Molin
 * @date 2021/12/27  13:20
 * class description: 商品中心用户接口
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    /**
     * 测试用户与商品中心连通性接口.
     * @return target string
     */
//    @SaCheckPermission(value = "user-add")
    @GetMapping("/testAction")
    public String testAction() {
       if (StpUtil.isLogin()) {
           return "连通性良好";
       }
       return "连通性不稳";
    }
}
