package com.mini.app.common.config;

import com.alibaba.fastjson.JSON;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import com.mini.app.common.utils.JWTUtils;
import com.mini.app.common.utils.RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author liyunlng
 * @ClassName: AuthInterceptor
 * @Description:
 * @date 2020/10/26
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(AuthInterceptor.class);

    /**
     * @Description 拦截 controller
     * @Date 14:30 2020/10/26
     * @Param [request, response, handler]
     * @return boolean
     **/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            requestWrapper = new RequestWrapper(request);
            String data = RequestWrapper.ReadAsChars(requestWrapper);

            String userIdStr = "";
            String token = "";
            if (StringUtils.isEmpty(data)) {
                userIdStr = request.getParameter("userId");
                token = request.getParameter("token");
            } else {
                Map<String, Object> map = JSON.parseObject(data);
                token = map.get("token").toString();
                userIdStr = map.get("userId").toString();
            }

            // 验证Token是否有效
            String appUID = JWTUtils.getAppUID(token);
            if (!userIdStr.equals(appUID)) throw new ApiException(Result.TOKEN_ERROR);
            return true;
        }
        return false;
    }
}
