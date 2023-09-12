package ppc.security_framework.intercepter_strategy;

import ppc.security_framework.interceptor.CustomInterceptorAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomInterceptStrategyFactory{
    private final List<CustomInterceptorAdapter> interceptors= new ArrayList<>();

    public CustomInterceptStrategyFactory addInterceptor(CustomInterceptorAdapter interceptor){
        this.interceptors.add(interceptor);
        return this;
    }

    public CustomInterceptStrategy build(){
        return new CustomInterceptStrategy(interceptors);
    }
}
