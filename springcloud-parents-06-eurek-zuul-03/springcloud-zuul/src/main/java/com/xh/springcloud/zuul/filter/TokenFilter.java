package com.xh.springcloud.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/18
 */
@Component
public class TokenFilter extends ZuulFilter {
    // 编写过滤器拦截业务逻辑代码
    public Object run() throws ZuulException {
        // 案例：拦截所有的服务接口，判断服务接口上是否有传递userToken参数

        // 1.获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        // 2.获取 Request
        HttpServletRequest request = currentContext.getRequest();
        // 3.获取token 的时候 从请求头中获取
        String userToken = request.getParameter("userToken");
        if (StringUtils.isEmpty(userToken)) {
            // 不会继续执行... 不会去调用服务接口，网关服务直接响应给客户端
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody("userToken is null");
            currentContext.setResponseStatusCode(401);
            return null;
            // 返回一个错误提示
        }
        // 正常执行调用其他服务接口...
        return null;
    }

    public boolean shouldFilter() {

        return true;
    }

    // 过滤器执行顺序,当一个请求在同一个阶段的时候存在多个过滤器的时候，多个过滤器执行顺序
    public int filterOrder() {
        return 0;
    }

    // 过滤类型 pre 表示在请求之前进行执行
    @Override
    public String filterType() {
        return "pre";
    }

    // 网关过滤器如何编写
}
