package com.mini.app.common.entity.vx;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @ClassName: VXCode
 * @Description:
 * @date 2020/10/22
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class VXCode {
    // 小程序 appId
    private String appid;

    // 小程序 appSecret
    private String secret;

    // 登录时获取的 code
    private String js_code;

    // 授权类型，此处只需填写 authorization_code
    private String grant_type;

}
