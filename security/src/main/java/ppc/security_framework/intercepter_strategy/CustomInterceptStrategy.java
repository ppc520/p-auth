package ppc.security_framework.intercepter_strategy;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// TODO: 2023/9/8 custom策略已完成，开始写token生成拦截器，用于返回token
public class CustomInterceptStrategy implements WebMvcConfigurer {
    private List<HandlerInterceptor> interceptors;
    public CustomInterceptStrategy(List<HandlerInterceptor> interceptors){
        this.interceptors = interceptors;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        for (HandlerInterceptor interceptor : interceptors) {
            registry.addInterceptor(interceptor);
        }
    }
}
