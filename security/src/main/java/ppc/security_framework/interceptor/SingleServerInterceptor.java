package ppc.security_framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ppc.security_framework.exception.UnAuthorizeException;
import ppc.security_framework.util.AuthenticationUtil;
import ppc.security_framework.util.JWTUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SingleServerInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先判断该请求是否包含refreshToken，如果包含则说明是用来认证的请求，如果不包含，则说明是用来认证的请求
        if (authenticationUtil.isLoginPath(request)){
            return true;
        }else if (authenticationUtil.hasRefreshToken(request)){
            String username = authenticationUtil.getUsernameFromServlet(request);
            if (username==null||(!authenticationUtil.isUnexpiredRefreshToken(request))||(!authenticationUtil.isValidRefreshToken(request))){
                throw new UnAuthorizeException("token无效");
            }
            return true;
        }else{
            String token=request.getHeader(jwtUtil.getAccessTokenName());
            if (!jwtUtil.isValidAccessToken(token)){
                throw new UnAuthorizeException("token无效");
            }else if (!jwtUtil.isUnexpired(token)){
                throw new UnAuthorizeException("token已过期");
            }return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (authenticationUtil.isLoginPath(request)&& authenticationUtil.isAuthenticateSuccess(modelAndView)){
            authenticationUtil.addTokensToHeader(request,response);
        }else if ((!authenticationUtil.isLoginPath(request))&&authenticationUtil.isAuthenticateSuccess(modelAndView)&&authenticationUtil.hasRefreshToken(request)){
            authenticationUtil.refreshAccessToken(request,response);
        }
    }
}
