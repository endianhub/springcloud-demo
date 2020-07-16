package com.xh.springcloud.api.service.impl;

import com.xh.springcloud.api.service.member.MemberService;
import com.xh.springcloud.common.base.BaseApiService;
import com.xh.springcloud.common.response.ResponseBase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/10
 */
@RestController
public class MemberServiceImpl extends BaseApiService implements MemberService {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public ResponseBase getMember(@RequestParam("name") String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("serverPort", serverPort);
        map.put("Description", "我是会员服务！" + System.currentTimeMillis());
        return setResultSuccess(map);
    }

    @Override
    public ResponseBase getUserInfo() {
        System.out.println("我是会员服务,会员服务调用订单服务开始啦！！");
        try {
            // 模拟请求延时
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return setResultSuccess("订单服务接口调用会员服务接口成功....");
    }


}
