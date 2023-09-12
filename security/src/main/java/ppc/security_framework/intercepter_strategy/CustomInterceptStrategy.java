package ppc.security_framework.intercepter_strategy;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ppc.security_framework.interceptor.CustomInterceptorAdapter;

import java.util.List;

public class CustomInterceptStrategy implements WebMvcConfigurer,CommonAccessControlStrategy {
    private List<CustomInterceptorAdapter> interceptors;
    public CustomInterceptStrategy(List<CustomInterceptorAdapter> interceptors){
        this.interceptors = interceptors;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        for (HandlerInterceptor interceptor : interceptors) {
            registry.addInterceptor(interceptor);
        }
    }
}
