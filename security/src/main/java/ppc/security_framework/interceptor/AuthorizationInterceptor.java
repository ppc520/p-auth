package ppc.security_framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import ppc.security_framework.exception.UnAuthorizeException;
import ppc.security_framework.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader(jwtUtil.getAccessTokenName());
        if (!jwtUtil.isValidAccessToken(token)){
            throw new UnAuthorizeException("token无效");
        }else if (!jwtUtil.isUnexpired(token)){
            throw new UnAuthorizeException("token已过期");
        }return true;
    }
}
