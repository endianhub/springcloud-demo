package com.xh.springcloud.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Title: 服务提供者1
 * Description:
 * 分别以不同的端口启动不同的服务实现集群部署
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/10
 */
@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients
public class MemberApplication1 {


    public static void main(String[] args) {
        SpringApplication.run(MemberApplication1.class, args);
    }

}
