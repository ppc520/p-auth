package ppc.security_framework.util;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.ModelAndView;
import ppc.security_framework.auto_import_params.AuthenticationConfig;
import ppc.security_framework.auto_import_params.ResultEntityConfig;
import ppc.security_framework.auto_import_params.enums.UsernameFieldLocationEnum;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Component
public class AuthenticationUtil {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationConfig authenticationConfig;
    @Autowired
    private ResultEntityConfig resultEntityConfig;

    private String usernameFieldName;
    private String loginPath;
    private Boolean pathStrictMatching;
    private UsernameFieldLocationEnum usernameFieldLocation;

    @PostConstruct
    private void init(){
        this.usernameFieldName=authenticationConfig.getUsernameFieldName();
        this.loginPath=authenticationConfig.getLoginPath();
        this.pathStrictMatching=authenticationConfig.getPathStrictMatching();
        this.usernameFieldLocation=authenticationConfig.getUsernameFieldLocation();
    }

    public void addTokensToHeader(HttpServletRequest request, HttpServletResponse response) {
        String username=getUsernameFromServlet(request);
        String accessToken = jwtUtil.getAccessToken(username);
        String refreshToken = jwtUtil.getRefreshToken(username);
        response.setHeader(jwtUtil.getAccessTokenName(), accessToken);
        response.setHeader(jwtUtil.getRefreshTokenName(), refreshToken);
    }

    public boolean isAuthenticateSuccess(ModelAndView modelAndView){
        String code = (String) modelAndView.getModel().get(resultEntityConfig.getCodeFieldName());
        if (code.equals(authenticationConfig.getCodeFieldNameSuccessCode())){
            return true;
        }return false;
    }

    public boolean hasRefreshToken(HttpServletRequest request){
        return !ObjectUtils.isEmpty(request.getHeader(jwtUtil.getRefreshTokenName()));
    }

    public String getUsernameFromServlet(HttpServletRequest request) {
        UsernameFieldLocationEnum fieldEnum = getUsernameFieldLocation();
        String username=null;
        //从请求体中拿到username
        if (fieldEnum.equals(UsernameFieldLocationEnum.requestbody)){
            BufferedReader reader=null;
            StringBuilder requestBody = null;
            try {
                requestBody = new StringBuilder();
                reader = request.getReader();
                String line;
                while ((line = reader.readLine()) != null) {
                    requestBody.append(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            JSONObject jsonObject = JSONObject.parseObject(requestBody.toString());
            username = jsonObject.getString(getUsernameFieldName());
            return username;
        }
        //从请求参数中拿到username
        else if (fieldEnum.equals(UsernameFieldLocationEnum.requestparam)){
            username=request.getParameter(getUsernameFieldName());
            return username;
        }
        return username;
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

    public boolean isValidRefreshToken(HttpServletRequest request) {
        String refreshToken = request.getHeader(jwtUtil.getRefreshTokenName());
        return jwtUtil.isValidRefreshToken(refreshToken);
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
