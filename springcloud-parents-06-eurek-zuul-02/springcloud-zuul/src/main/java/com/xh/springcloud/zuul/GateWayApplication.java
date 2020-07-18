package com.xh.springcloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/18
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GateWayApplication {

    // @EnableZuulProxy 开启网关代理

    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }

    // zuul配置能够使用config实现实时更新
    @RefreshScope
    @ConfigurationProperties("zuul")
    public ZuulProperties zuulProperties() {
        return new ZuulProperties();
    }
}
