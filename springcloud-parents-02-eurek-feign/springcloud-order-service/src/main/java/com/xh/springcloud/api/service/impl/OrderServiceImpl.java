package com.xh.springcloud.api.service.impl;

import com.xh.springcloud.api.feign.MemberServiceFeigin;
import com.xh.springcloud.api.service.order.OrderService;
import com.xh.springcloud.common.base.BaseApiService;
import com.xh.springcloud.common.response.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/10
 */
@RestController
public class OrderServiceImpl extends BaseApiService implements OrderService {

    @Autowired
    private MemberServiceFeigin memberServiceFeigin;

    @Override
    public String orderToMember(String name) {
        ResponseBase resp = memberServiceFeigin.getMember(name);
        return resp == null ? "没有找到用户信息" : resp.toString();
    }

    // 没有解决服务雪崩效应
    // 此方法有模拟延时加载，feign客户端默认等待时间为1秒，所以超出时间后会报异常，想要解决此问题需要修改超时时间
    @Override
    public ResponseBase orderToMemberUserInfo() {
        System.out.println("orderToMemberUserInfo:" + "线程池名称:" + Thread.currentThread().getName());
        // 此方法有模拟延迟加载
        return memberServiceFeigin.getUserInfo();
    }

}
