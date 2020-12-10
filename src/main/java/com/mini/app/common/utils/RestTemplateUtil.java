/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: RestTemplateUtil.java
 * @author: zhangyq
 * @date: 2020/12/3
 * @Copyright: 2020/12/3 www.rongdasoft.com Inc. All rights reserved.
 */
package com.mini.app.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author zhangyq
 * @ClassName: RestTemplateUtil
 * @date 2020/12/3
 */
@Component(value = "RestTemplateUtil")
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate;


    public <R> R sendRequest(Map param, String url, ParameterizedTypeReference<R> typeRef) {
        HttpEntity<Map> httpEntity = new HttpEntity<>(param);
        ResponseEntity<R> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity
                , typeRef);

        return responseEntity.getBody();
    }


    public <R> R sendSaasRequest(Map param, String url, ParameterizedTypeReference<R> typeRef) {
        return sendRequest(param, "" + url, typeRef);
    }

    // application/x-www-form-urlencoded 格式
    public <T> T sendSaasRequestByHeader(MultiValueMap<String, Object> param, String url, Class<T> type) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        HttpEntity<Map> httpEntity = new HttpEntity<>(param, headers);

        return restTemplate.postForObject(url, httpEntity, type);
    }
}
