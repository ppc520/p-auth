package ppc.security_framework.intercepter_strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ppc.security_framework.access_control_model.AccessControlEntity;
import ppc.security_framework.interceptor.AuthorizationInterceptor;

public class AuthorizationServerInterceptStrategy implements WebMvcConfigurer,CommonAccessControlStrategy {
    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }
}
