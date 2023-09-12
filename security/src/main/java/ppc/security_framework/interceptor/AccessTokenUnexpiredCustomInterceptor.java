package ppc.security_framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ppc.security_framework.exception.UnAuthorizeException;
import ppc.security_framework.util.JWTUtil;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AccessTokenUnexpiredCustomInterceptor implements CustomInterceptorAdapter {
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
        String access_token=request.getHeader(accessTokenName);
        if (!jwtUtil.isUnexpired(access_token)){
            throw new UnAuthorizeException("token已过期");
        }
        return true;
    }
}
