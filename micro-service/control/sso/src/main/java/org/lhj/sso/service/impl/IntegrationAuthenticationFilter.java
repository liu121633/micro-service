package org.lhj.sso.service.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 刘洪君
 * @date 2019/3/29 9:05
 * 认证拦截器
 */
public class IntegrationAuthenticationFilter extends GenericFilterBean {


    private static final String OAUTH_TOKEN_URL = "/oauth/token";
    private static final String GRANT_TYPE = "grant_type";
    private static final String GRANT_TYPE_VAELUE = "password";


    private ApplicationContext applicationContext;

    private RequestMatcher requestMatcher;

    public IntegrationAuthenticationFilter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, "POST")
        );
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //做过滤 只处理特定请求
        if (requestMatcher.matches(request)) {
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

}