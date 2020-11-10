package com.mini.app.sys.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mini.app.common.entity.manage.ManageUser;
import com.mini.app.common.entity.user.Role;
import com.mini.app.common.entity.user.User;
import com.mini.app.common.entity.user.UserRole;
import com.mini.app.common.entity.vx.VXCode;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import com.mini.app.common.utils.JWTUtils;
import com.mini.app.sys.controller.LoginController;
import com.mini.app.sys.dao.ManageUserDao;
import com.mini.app.sys.dao.UserDao;
import com.mini.app.sys.dao.UserRoleDao;
import com.mini.app.sys.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

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

    @Autowired
    private ManageUserDao manageUserDao;

    @Autowired
    private UserRoleDao userRoleDao;

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
        map.put("roleId", null);
        return map;
    }

    /**
     * @param data
     * @return java.lang.Object
     * @Author liyunlong
     * @Description //TODO
     * @Date 12:01 2020/11/5
     * @Param [data]
     */
    @Override
    public Map<String, Object> loninManageSys(ManageUser data) {
        Map<String, Object> map = new HashMap<>();
        String manageAccound = data.getManageAccound();
        String managePwd = data.getManagePwd();

        Example example = new Example(ManageUser.class);
        example.createCriteria()
                .andEqualTo("manageAccound", manageAccound)
                .andEqualTo("managePwd", managePwd);

        ManageUser manageUser = manageUserDao.selectOneByExample(example);
        if (manageUser == null) {
            throw new ApiException(Result.PWD_ERROR);
        }
        // 查询用户角色权限
        Integer manageId = manageUser.getManageId();
        Example roleExample = new Example(UserRole.class);
        roleExample.createCriteria()
                .andEqualTo("userId", manageId);
        UserRole userRole
                = userRoleDao.selectOne(new UserRole().setUserId(manageId));
        Integer roleId = null;
        if (userRole != null) {
            roleId = userRole.getRoleId();
        }
        String token = JWTUtils.createToken(manageUser);
        map.put("manageId", manageUser.getManageId());
        map.put("token", token);
        map.put("userName", manageUser.getManageName());
        map.put("roleId", roleId);

        return map;
    }
}
