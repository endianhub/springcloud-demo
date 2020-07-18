package com.xh.springcloud.api.service.member;

import com.xh.springcloud.common.response.ResponseBase;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Title:
 * Description:
 *
 * @author H.Yang
 * @email xhaimail@163.com
 * @date 2020/7/14
 */
public interface MemberService {
    // 实体类是存放接口项目还是 存放在实现项目 实体类存放在接口项目里面
    // 实体类和定义接口信息存放在接口项目
    // 代码实现存放在接口实现类里面

    @RequestMapping("/getMember")
    public ResponseBase getMember(@RequestParam("name") String name);

    @RequestMapping("/getUserInfo")
    public ResponseBase getUserInfo();
}
