package com.mini.app.common.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mini.app.common.utils.RequestWrapper;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

/**
 * @author wangshipeng
 * @ClassName: XSSFilter
 */

public class XSSFilter implements Filter {

    /**
     */
    private static String xssEncode(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\"'][\\s]*javascript:(.*)[\"']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        return value;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jsonBody = RequestWrapper.ReadAsChars(request);

        if (StringUtils.isBlank(jsonBody)) {
            chain.doFilter(request, response);
            return;
        }
        Map<String, Object> data = JSON.parseObject(jsonBody);
        recursionReplaceXssValue(data);
        if (request instanceof RequestWrapper) {
            RequestWrapper wrapper = ((RequestWrapper) request);
            String dataStr = JSONObject.toJSONString(data);
            if (StringUtils.isNotBlank(dataStr)) {
                wrapper.setRequestBody(dataStr.getBytes(Charset.forName("UTF-8")));
            }
        }

        chain.doFilter(request, response);

    }


    /**
     * @param data
     * @return
     * @Description 递归替换xss内容
     * @author wangshipeng
     * @date 2019年07月15日 16:39:00
     */
    private void recursionReplaceXssValue(Map<String, Object> data) {
        if (MapUtils.isEmpty(data)) {
            return;
        }


        data.forEach((k, v) -> {
            if (Objects.isNull(v)) {
                return;
            }
            if (v instanceof String) {
                data.put(k, xssEncode((String) v));
            } else if (v instanceof Map) {
                recursionReplaceXssValue((Map) v);

            }
        });

    }

    @Override
    public void destroy() {
    }

}
