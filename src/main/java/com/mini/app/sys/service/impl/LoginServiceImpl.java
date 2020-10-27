package com.mini.app.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mini.app.common.entity.user.User;
import com.mini.app.common.entity.vx.VXCode;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import com.mini.app.common.utils.JWTUtils;
import com.mini.app.sys.controller.LoginController;
import com.mini.app.sys.dao.UserDao;
import com.mini.app.sys.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liyunlng
 * @ClassName: LoginServiceImpl
 * @Description:
 * @date 2020/10/22
 */
@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    public static String VX_API = "https://api.weixin.qq.com/sns/jscode2session?";

    @Autowired
    private UserDao userDao;

    /**
     * @param data
     * @return com.mini.app.common.entity.user.User
     * @Author liyunlong
     * @Description 登陆接口
     * @Date 16:41 2020/10/22
     * @Param [data]
     */
    @Override
    public Map<String, Object> loninSys(VXCode data) {
        Map<String, Object> map = new HashMap<>();
        // 1. 获取vx传过来参数值
        String appid = data.getAppid();
        String grant_type = data.getGrant_type();
        String js_code = data.getJs_code();
        String secret = data.getSecret();

        // 请求VX接口拿到用户位置：openid等
        RestTemplate restTemplate = new RestTemplate();
        String url = VX_API + "appid=" +appid+ "&secret=" +secret + "&js_code=" +js_code+ "&grant_type=" + grant_type;
        log.info("请求地址：{}", url);

        String vxString = restTemplate.getForObject(url, String.class);
        Map<String, Object> vxMap = JSONObject.parseObject(vxString);
        log.info("VX返回参数：{}", vxMap);
        Object errcode = vxMap.get("openid");
        if (null == errcode) {
            throw  new ApiException(Result.VX_ERRO);
        }

        String openid = (String)vxMap.get("openid");
        User user = new User();
        user.setOpenid(openid);

        // 1. 先去数据查询用户是否存在
        User loginUser = userDao.selectOne(user);
        if (loginUser == null) {
            // 2.1 不存在就新增用户信息返回Token
            user.setCreateTime(new Date());
            userDao.insertSelective(user);
            String token = JWTUtils.createToken(user);
            map.put("userId", user.getUserId());
            map.put("token", token);
            return map;

        }
        // 2. 存在则将token返回 ，刷新token
        String token = JWTUtils.createToken(loginUser);
        map.put("userId", loginUser.getUserId());
        map.put("token", token);
        map.put("userName", loginUser.getUserName());
        return map;
    }
}
