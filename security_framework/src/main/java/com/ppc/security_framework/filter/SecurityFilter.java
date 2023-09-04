package com.ppc.security_framework.filter;

import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String token=((HttpServletRequest)servletRequest).getHeader("token");
        String curPath=((HttpServletRequest)servletRequest).getRequestURI();
        if (curPath.contains("swagger")||curPath.contains("api-docs")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else if (ObjectUtils.isEmpty(token)&&!curPath.contains("/login")){
            ((HttpServletResponse)servletResponse).setStatus(401);
        }else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

