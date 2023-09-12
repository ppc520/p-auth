package ppc.security_framework.intercepter_strategy;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ppc.security_framework.access_control_model.AccessControlEntity;
import ppc.security_framework.context.AccessControlEntityContextHolder;

//该类在需要授权的时候被继承，认证服务器策略无需继承
public interface CommonAccessControlStrategy{
    default void setAccessControlModel(AccessControlEntity entity) {
        AccessControlEntityContextHolder.setContextHolder(entity);
    }
}
