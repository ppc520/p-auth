package security.ppc.security_framework.filter;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;
import security.ppc.security_framework.entity.SecurityErrorCommonResult;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String accessToken=((HttpServletRequest)servletRequest).getHeader("access-token");
        String curPath=((HttpServletRequest)servletRequest).getRequestURI();
        if (curPath.contains("swagger")||curPath.contains("api-docs")){
            filterChain.doFilter(servletRequest,servletResponse);
        }
        else if (ObjectUtils.isEmpty(accessToken)&&!curPath.contains("/login")){
            ((HttpServletResponse)servletResponse).setStatus(401);
            ((HttpServletResponse)servletResponse).setHeader("content-type", "text/html;charset=utf-8");
            ((HttpServletResponse)servletResponse).getWriter().write(JSON.toJSONString(SecurityErrorCommonResult.noAuthErr()));
        } else{
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
