package com.mini.app.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mini.app.common.entity.user.User;
import com.mini.app.common.enums.Result;
import com.mini.app.common.exception.ApiException;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liyunlng
 * @ClassName: JWTUtils
 * @Description:
 * @date 2020/10/22
 */
public class JWTUtils {

    /** token秘钥 */
    public static final String SECRET = "HELLO";

    /** token 过期时间: 7天 */
    public static final int TIME_TYPE = Calendar.DATE;

    public static final int PAST_TIME = 7;


    public static String createToken(User user) {
        Date iatDate = new Date();
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(TIME_TYPE, PAST_TIME);
        // 过期天数
        Date expiresDate = nowTime.getTime();

        // header Map
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String userId = user.getUserId().toString();
        String openid = user.getOpenid();

        // build token
        // param backups {iss:Service, aud:APP}
        String token = JWT.create().withHeader(map) // header
                .withClaim("iss", "Service") // payload
                .withClaim("aud", "APP")
                .withClaim("userId", null == userId ? null : userId)
                .withClaim("openid", null == openid ? null : openid)
                .withIssuedAt(iatDate) // sign time
                .withExpiresAt(expiresDate) // expire time
                .sign(Algorithm.HMAC256(SECRET)); // signature

        return token;
    }

    /**
     * @Description 验证Token是否合法
     * @Date 18:08 2020/10/22
     **/
    public static String getAppUID(String token) {
        Map<String, Claim> claims = verifyToken(token);
        Claim userId = claims.get("userId");
        Claim openid = claims.get("openid");
        if (StringUtils.isEmpty(userId) && StringUtils.isEmpty(openid)) {
            // token 校验失败, 抛出Token验证非法异常
            throw new ApiException(Result.TOKEN_ERROR);
        }
        return userId.asString();
    }

    public static Map<String, Claim> verifyToken(String token) {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new ApiException(Result.TOKEN_ERROR);
        }
        return jwt.getClaims();
    }
}
