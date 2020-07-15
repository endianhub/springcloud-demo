package com.xh.springcloud.api.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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

    // 订单服务继承会员服务接口，用来实现feign客户端 减少重复接口代码
    @Autowired
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
    // Hystrix 有两种方式配置保护服务 通过注解和接口形式
    // fallbackMethod 方法的作用：服务降级执行
    // @HystrixCommand 默认开启线程池隔离方式,服务降级,服务熔断
    // 需要设置Hystrix服务超时时间，默认1秒，超出1秒则不能访问

    // 默认开启服务隔离方式 以线程池方式
    // 默认开启服务降级执行方法orderToMemberUserInfoHystrixFallback
    // 默认开启服务熔断机制
    @HystrixCommand(fallbackMethod = "orderToMemberUserInfoHystrixFallback")
    public ResponseBase orderToMemberUserInfoHystrix() {
        System.out.println("orderToMemberUserInfoHystrix:" + "线程池名称:" + Thread.currentThread().getName());
        return memberServiceFeigin.getUserInfo();
    }

    // 服务降级方法
    public ResponseBase orderToMemberUserInfoHystrixFallback() {
        return setResultSuccess("返回一个友好的提示：服务降级,服务器忙，请稍后重试!");
    }

    // 订单服务接口
    @Override
    public ResponseBase orderInfo() {
        System.out.println("orderToMemberUserInfoHystrix:" + "线程池名称:" + Thread.currentThread().getName());
        return setResultSuccess();
    }
}
