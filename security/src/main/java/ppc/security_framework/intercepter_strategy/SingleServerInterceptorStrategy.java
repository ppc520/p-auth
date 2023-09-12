package ppc.security_framework.intercepter_strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ppc.security_framework.interceptor.SingleServerInterceptor;

public class SingleServerInterceptorStrategy implements WebMvcConfigurer,CommonAccessControlStrategy {
    @Autowired
    private SingleServerInterceptor singleServerInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(singleServerInterceptor);
    }
}
