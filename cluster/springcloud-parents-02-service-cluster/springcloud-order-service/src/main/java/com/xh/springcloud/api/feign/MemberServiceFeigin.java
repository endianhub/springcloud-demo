package com.xh.springcloud.api.feign;

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
// api-member-service 是会员服务名称(会员服务注册到eureka的名称)
@FeignClient(value = "api-member-service")
public interface MemberServiceFeigin extends MemberService {

}
