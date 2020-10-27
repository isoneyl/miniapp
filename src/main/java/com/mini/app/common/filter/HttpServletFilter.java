package com.mini.app.common.filter;

import com.mini.app.common.utils.RequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @ClassName: HttpServletFilter
 * @Description: 过滤器
 */

public class HttpServletFilter implements Filter {


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		ServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
			requestWrapper = new RequestWrapper((HttpServletRequest)request);
		}

		if (requestWrapper == null) {
			chain.doFilter(request, response);
		} else {
			chain.doFilter(requestWrapper, response);
		}
		HttpServletRequest hRequest = (HttpServletRequest) request;
	}

	@Override
	public void destroy() {
	}

}
