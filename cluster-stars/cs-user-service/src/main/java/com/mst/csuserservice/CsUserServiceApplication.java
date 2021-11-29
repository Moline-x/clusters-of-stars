package com.mst.csuserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "com.mst.csuserservice.domain.mapper")
public class CsUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsUserServiceApplication.class, args);
    }

}
