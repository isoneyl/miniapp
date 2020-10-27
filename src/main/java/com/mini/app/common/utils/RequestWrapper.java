package com.mini.app.common.utils;

import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName: RequestWrapper
 * @Description: 从请求体中获取参数请求包装类
 */

public class RequestWrapper extends HttpServletRequestWrapper {

	private byte[] requestBody;// 用于将流保存下来

	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		requestBody = StreamUtils.copyToByteArray(request.getInputStream());


    }

    public void setRequestBody(byte[] requestBody) {
        this.requestBody = requestBody;
    }

	@Override
	public ServletInputStream getInputStream() throws IOException {

		final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);
		return new ServletInputStream() {
			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				return false;
			}

			@Override
			public boolean isReady() {
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {

			}
		};
	}

	@Override
	public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream(), StandardCharsets.UTF_8));
    }


	/**
	 * @Description 读取请求体内容
	 * @param request
	 */
	public static String ReadAsChars(ServletRequest request) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder("");
		try {
			br = request.getReader();
			String str;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}


    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        //处理中文乱码问题
        if (value != null) {
            value = StringUtils.replacer(value);
        }
        return value;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> valueMap = super.getParameterMap();
        Iterator iterator = valueMap.entrySet().iterator();
        Map<String, String[]> resMap = new HashMap<>();
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = (Map.Entry<String, String[]>) iterator.next();
            String key = entry.getKey();
            String[] value_arr = entry.getValue();
            handlerValues(value_arr);
            resMap.put(key, value_arr);
        }
        return resMap;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] arr = super.getParameterValues(name);
        if (arr != null) {
            handlerValues(arr);
        }
        return arr;
    }

    /**
     * @param arr
     * @return void
     * @Description 处理value值乱码
     **/
    private static void handlerValues(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            String value = arr[i];
            //处理中文乱码问题
            if (value != null) {
                value = StringUtils.replacer(value);
            }
            arr[i] = value;
        }
    }
}
