package com.xh.springcloud.api.fallback;

import com.xh.springcloud.api.feign.MemberServiceFeigin;
import com.xh.springcloud.common.base.BaseApiService;
import com.xh.springcloud.common.response.ResponseBase;
import org.springframework.stereotype.Component;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/15
 */
@Component
public class MemberServiceFallback extends BaseApiService implements MemberServiceFeigin {

    @Override
    public ResponseBase getMember(String name) {
        return null;
    }

    @Override
    public ResponseBase getUserInfo() {
        return setResultError("服务器忙,请稍后重试!");
    }
}
