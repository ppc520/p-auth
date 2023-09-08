package ppc.security_framework.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ppc.security_framework.auto_import_params.AuthenticationConfig;
import ppc.security_framework.auto_import_params.enums.UsernameFieldLocationEnum;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthenticationUtil {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationConfig authenticationConfig;

    private String usernameFieldName;
    private String loginPath;
    private Boolean pathStrictMatching;
    private UsernameFieldLocationEnum usernameFieldLocation;

    @PostConstruct
    public void init(){
        this.usernameFieldName=authenticationConfig.getUsernameFieldName();
        this.loginPath=authenticationConfig.getLoginPath();
        this.pathStrictMatching=authenticationConfig.getPathStrictMatching();
        this.usernameFieldLocation=authenticationConfig.getUsernameFieldLocation();
    }

    // TODO: 2023/9/8 用户登录，校验成功后进行授权，post方法后生效
    public void authenticate(String username, HttpServletResponse response) {
        String accessToken = jwtUtil.getAccessToken(username);
        String refreshToken = jwtUtil.getRefreshToken(username);
        response.setHeader(jwtUtil.getAccessTokenName(), accessToken);
        response.setHeader(jwtUtil.getRefreshTokenName(), refreshToken);
    }

    // TODO: 2023/9/8 后期需要将refreshToken放入redis中，通过判断refreshToken是否在redis中来判断是否过期
    public void refreshAccessToken(HttpServletRequest request, HttpServletResponse response) {
        String oldAccessToken = request.getHeader(jwtUtil.getAccessTokenName());
        String newAccessToken = jwtUtil.refreshAccessToken(oldAccessToken);
        response.setHeader(jwtUtil.getAccessTokenName(), newAccessToken);
    }

    public boolean isLoginPath(HttpServletRequest request) {
        return request.getRequestURI().contains(loginPath);
    }

    public boolean isUnexpiredRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(jwtUtil.getRefreshTokenName());
        return jwtUtil.isUnexpired(refreshToken);
    }

    public String getUsernameFieldName() {
        return usernameFieldName;
    }

    public String getLoginPath() {
        return loginPath;
    }

    public Boolean getPathStrictMatching() {
        return pathStrictMatching;
    }

    public UsernameFieldLocationEnum getUsernameFieldLocation() {
        return usernameFieldLocation;
    }
}
