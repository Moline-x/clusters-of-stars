package com.mst.cseureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CsEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsEurekaApplication.class, args);
    }

}
