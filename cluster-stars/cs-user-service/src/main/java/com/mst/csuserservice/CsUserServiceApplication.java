package com.mst.csuserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CsUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsUserServiceApplication.class, args);
    }

}
