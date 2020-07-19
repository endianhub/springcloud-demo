package com.xh.springcloud.api.feign;

import com.xh.springcloud.api.fallback.MemberServiceFallback;
import com.xh.springcloud.api.service.member.MemberService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/14
 */
@FeignClient(value = "api-member-service", fallback = MemberServiceFallback.class)
public interface MemberServiceFeigin extends MemberService {

    // 服务降级 熔断
    // 实体类是存放接口项目还是 存放在实现项目 实体类存放在接口项目里面
    // 实体类和定义接口信息存放在接口项目
    // 代码实现存放在接口实现类里面

}
