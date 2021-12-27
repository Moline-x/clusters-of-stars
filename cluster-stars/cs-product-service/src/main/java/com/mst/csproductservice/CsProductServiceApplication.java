package com.mst.csproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Molin
 * @date 2021/12/23  23:18
 * class description: 商品微服务启动类.
 */
@SpringBootApplication
@EnableEurekaClient
public class CsProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CsProductServiceApplication.class, args);
    }
}
