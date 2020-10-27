package com.mini.app.sys.service;

import com.mini.app.common.entity.user.User;
import com.mini.app.common.entity.vx.VXCode;

import java.util.Map;

/**
 * @author liyunlng
 * @ClassName: LoginService
 * @Description:
 * @date 2020/10/22
 */
public interface LoginService {
    /**
     * @Author liyunlong
     * @Description 登陆接口
     * @Date 16:41 2020/10/22
     * @Param [data]
     * @return com.mini.app.common.entity.user.User
     **/
    Map<String, Object> loninSys(VXCode data);

}
