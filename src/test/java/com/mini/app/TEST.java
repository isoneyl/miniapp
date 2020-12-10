package com.mini.app;

import com.mini.app.common.utils.RestTemplateUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liyunlng
 * @ClassName: TEST
 * @Description:
 * @date 2020/12/4
 */
@SpringBootTest
public class TEST {

    @Qualifier("RestTemplateUtil")
    public RestTemplateUtil restTemplateUtil;

    @Test
    public void test() {
        // 1. 获取参数，拼接参数
        String user_id = "41039";
        // 2. 初始化RestTemplate
        String url = "http://localhost:8080/sys/yxz/interface/listOrgByCrmId";
        // 4. 设置参数
        MultiValueMap<String, Object> pMap = new LinkedMultiValueMap<>();
        pMap.add("crmId", user_id);
        // 5. 发送请求参数返回
        Map result = restTemplateUtil.sendSaasRequestByHeader(pMap, url, Map.class);
        System.err.println(result);
    }
}
