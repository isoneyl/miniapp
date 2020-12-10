/**
 * All rights Reserved, Designed By www.rongdasoft.com
 *
 * @version V1.0
 * @Title: RestTemplateConfig.java
 * @author: zhangyq
 * @date: 2020/12/3
 * @Copyright: 2020/12/3 www.rongdasoft.com Inc. All rights reserved.
 */
package com.mini.app.common.config;

import com.mini.app.common.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: RestTemplateConfig
 * @author zhangyq
 * @date 2020/12/3
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(@Qualifier("simpleClientHttpRequestFactory") ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.setObjectMapper(JsonUtil.getObjectMapper());
        MediaType[] mediaTypes = new MediaType[]{
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_OCTET_STREAM,

                MediaType.TEXT_HTML,
                MediaType.TEXT_PLAIN,
                MediaType.TEXT_XML,
                MediaType.APPLICATION_ATOM_XML,
                MediaType.APPLICATION_FORM_URLENCODED
        };

        jsonConverter.setSupportedMediaTypes(Arrays.asList(mediaTypes));

        converters.add(jsonConverter);
        restTemplate.setMessageConverters(converters);

        return restTemplate;
    }


    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(60000);// ms
        factory.setConnectTimeout(60000);// ms
        return factory;
    }
}
