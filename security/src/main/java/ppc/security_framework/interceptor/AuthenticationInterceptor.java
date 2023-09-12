package ppc.security_framework.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ppc.security_framework.exception.UnAuthorizeException;
import ppc.security_framework.util.AuthenticationUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthenticationUtil authenticationUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是非登录接口，就默认是携带refreshToken刷新accessToken
        if (authenticationUtil.isLoginPath(request)){
            return true;
        }else {
            String username = authenticationUtil.getUsernameFromServlet(request);
            if (username==null||(!authenticationUtil.isUnexpiredRefreshToken(request))||(!authenticationUtil.isValidRefreshToken(request))){
                throw new UnAuthorizeException("token无效");
            }
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (authenticationUtil.isLoginPath(request)&& authenticationUtil.isAuthenticateSuccess(modelAndView)){
            authenticationUtil.addTokensToHeader(request,response);
        }else if (!authenticationUtil.isLoginPath(request)&&authenticationUtil.isAuthenticateSuccess(modelAndView)){
            authenticationUtil.refreshAccessToken(request,response);
        }
    }
}
