package ppc.security_framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ppc.security_framework.util.AuthenticationUtil;
import ppc.security_framework.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return authenticationUtil.isLoginPath(request) || authenticationUtil.isUnexpiredRefreshToken(request);
        // TODO: 2023/9/8 将拦截到的权限列表放入threadlocal中 
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (authenticationUtil.isLoginPath(request)){
            // TODO: 2023/9/8 加上登录拦截逻辑 
        }
        authenticationUtil.refreshAccessToken(request,response);
    }
}
