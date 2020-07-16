package com.xh.springcloud.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/10
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class OrderAppllcation {

    public static void main(String[] args) {
        SpringApplication.run(OrderAppllcation.class, args);
    }

}
