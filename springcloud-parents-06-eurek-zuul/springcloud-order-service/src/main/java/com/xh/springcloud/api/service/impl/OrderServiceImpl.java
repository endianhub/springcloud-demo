package com.xh.springcloud.api.service.impl;

import com.xh.springcloud.api.feign.MemberServiceFeigin;
import com.xh.springcloud.api.service.order.OrderService;
import com.xh.springcloud.common.base.BaseApiService;
import com.xh.springcloud.common.response.ResponseBase;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    // 订单服务继承会员服务接口，用来实现feign客户端 减少重复接口代码
    @Resource
    private MemberServiceFeigin memberServiceFeigin;

    @Override
    public String orderToMember(String name) {
        ResponseBase resp = memberServiceFeigin.getMember(name);
        return resp == null ? "没有找到用户信息" : resp.toString();
    }

    // 没有解决服务雪崩效应
    @Override
    public ResponseBase orderToMemberUserInfo() {
        return memberServiceFeigin.getUserInfo();
    }

    // 解决服务雪崩效应
    @Override
    public ResponseBase orderToMemberUserInfoHystrix() {
        System.out.println("orderToMemberUserInfoHystrix:" + "线程池名称:" + Thread.currentThread().getName());
        return memberServiceFeigin.getUserInfo();
    }

    // 订单服务接口
    @Override
    public ResponseBase orderInfo() {
        System.out.println("orderToMemberUserInfoHystrix:" + "线程池名称:" + Thread.currentThread().getName());
        return setResultSuccess();
    }
}
