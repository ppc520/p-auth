package ppc.security_framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import ppc.security_framework.exception.UnAuthorizeException;
import ppc.security_framework.util.JWTUtil;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RefreshTokenUnexpiredInterceptor implements HandlerInterceptor {
    @Autowired
    private JWTUtil jwtUtil;
    private String accessTokenName;
    private String refreshTokenName;
    @PostConstruct
    private void init(){
        this.accessTokenName= jwtUtil.getAccessTokenName();
        this.refreshTokenName= jwtUtil.getRefreshTokenName();
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String refreshToken=request.getHeader(refreshTokenName);
        if (!jwtUtil.isUnexpired(refreshToken)){
            throw new UnAuthorizeException("token过期");
        }
        return true;
    }
}
