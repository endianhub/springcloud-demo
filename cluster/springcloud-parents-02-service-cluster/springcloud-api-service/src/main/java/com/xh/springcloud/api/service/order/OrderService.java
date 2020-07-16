package com.xh.springcloud.api.service.order;

import com.xh.springcloud.common.response.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/14
 */
public interface OrderService {

    // 订单服务调用会员服务接口信息 feign
    @RequestMapping("/orderToMember")
    public String orderToMember(String name);

    // 订单服务接口调用会员服务接口
    @RequestMapping("/orderToMemberUserInfo")
    public ResponseBase orderToMemberUserInfo();

}
