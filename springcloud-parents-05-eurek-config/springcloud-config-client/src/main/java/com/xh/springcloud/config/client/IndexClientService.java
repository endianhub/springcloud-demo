package com.xh.springcloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/15
 */
@RestController
//刷新配置需要此注解
@RefreshScope
public class IndexClientService {

    @Value("${data.env:error}")
    private String env;

    @GetMapping("/info")
    public String getInfo() {
        return env;
    }
}
