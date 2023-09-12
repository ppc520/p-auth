package com.ppc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ppc.security_framework.access_control_model.AccessControlEntity;
import ppc.security_framework.access_control_model.RoleBaseAccessControlEntityBuilder;
import ppc.security_framework.intercepter_strategy.*;
import ppc.security_framework.interceptor.AccessTokenValidCustomInterceptor;
import ppc.security_framework.interceptor.RefreshTokenUnexpiredCustomInterceptor;

@Configuration
public class SecurityConfig {
    //用于配置用户自定义策略，需要设置用户设置一个entity传入该Bean
    @Bean
    CustomInterceptStrategy getCustomStrategy(){
        AccessControlEntity entity=
                new RoleBaseAccessControlEntityBuilder()
                .setRole("ppc")
                .setPermission("555")
                .build();
        CustomInterceptStrategy strategy=
                new CustomInterceptStrategyFactory()
                .addInterceptor(new AccessTokenValidCustomInterceptor())
                .addInterceptor(new RefreshTokenUnexpiredCustomInterceptor())
                .build();
        strategy.setAccessControlModel(entity);
        return strategy;
    }

    //用于配置单机服务器策略，需要设置用户设置一个entity传入该Bean
    @Bean
    SingleServerInterceptorStrategy getSingleStrategy(){
        AccessControlEntity entity=
                new RoleBaseAccessControlEntityBuilder()
                        .build();
        SingleServerInterceptorStrategy strategy=
                new SingleServerInterceptorStrategy();
        strategy.setAccessControlModel(entity);
        return strategy;
    }

    //用于配置授权服务器策略，需要设置用户设置一个entity传入该Bean
    @Bean
    AuthorizationServerInterceptStrategy authorizationServerInterceptStrategy(){
        AccessControlEntity entity=
                new RoleBaseAccessControlEntityBuilder()
                .setRole("ppc")
                .setPermission("jjjj")
                .build();
        AuthorizationServerInterceptStrategy strategy=
                new AuthorizationServerInterceptStrategy();
        strategy.setAccessControlModel(entity);
        return strategy;
    }

    //用于配置认证服务器策略
    @Bean
    AuthenticationServerInterceptStrategy authenticationServerInterceptStrategy(){
        AuthenticationServerInterceptStrategy strategy=
                new AuthenticationServerInterceptStrategy();
        return strategy;
    }
}
