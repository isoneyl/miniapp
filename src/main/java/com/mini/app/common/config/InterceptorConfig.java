package com.mini.app.common.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: InterceptorConfig
 * @Description: 拦截器配置
 * @date 2020/10/26
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration
                = registry.addInterceptor(new AuthInterceptor());
        configNoInterceptor(interceptorRegistration);
    }

    private void configNoInterceptor(InterceptorRegistration interceptorRegistration) {
        // 登陆地址不拦截
        interceptorRegistration.excludePathPatterns("/sys/login");
        // 查看头像接口不拦截
        interceptorRegistration.excludePathPatterns("/user/imageView/**");
        // 上传图片不拦截
        interceptorRegistration.excludePathPatterns("/user/uploadImg");
        // 放行/error，不然会被拦截
        interceptorRegistration.excludePathPatterns("/error");
        // 拦截全部路径
        interceptorRegistration.addPathPatterns("/**");
    }

}
