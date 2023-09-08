package ppc.security_framework.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import ppc.security_framework.intercepter_strategy.CustomInterceptStrategy;

import java.util.ArrayList;
import java.util.List;

public class CustomInterceptStrategyFactory{
    private final List<HandlerInterceptor> interceptors= new ArrayList<>();

    public CustomInterceptStrategyFactory addInterceptor(HandlerInterceptor interceptor){
        this.interceptors.add(interceptor);
        return this;
    }

    public CustomInterceptStrategy build(){
        return new CustomInterceptStrategy(interceptors);
    }
}
