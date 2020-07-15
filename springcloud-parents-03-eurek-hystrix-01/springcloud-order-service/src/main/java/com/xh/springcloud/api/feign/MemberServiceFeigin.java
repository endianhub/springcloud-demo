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
@FeignClient(value = "api-member-service")
public interface MemberServiceFeigin extends MemberService {

}
