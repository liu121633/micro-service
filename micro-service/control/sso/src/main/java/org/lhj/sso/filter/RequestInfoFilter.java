package org.lhj.sso.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘洪君
 * @date 2019/2/22
 */
@Slf4j
@Order(Integer.MIN_VALUE)
@Component
public class RequestInfoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Enumeration<String> headerNames = request.getHeaderNames();

        StringBuffer requestURL = request.getRequestURL();
        log.info("requestURL:{}", requestURL);
        ObjectMapper objectMapper = new ObjectMapper();

        Enumeration<String> attributeNames = request.getAttributeNames();
        Map<String, Object> attributes = new HashMap<>(16);
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object value = request.getAttribute(name);
            attributes.put(name, value);
        }
        try {
            log.info("Http-RequestParam:\n{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(attributes));
        } catch (JsonProcessingException e) {
            log.error("Http-RequestParam:\n{}", "格式化输出失败");
            attributes.forEach((k, v) -> System.out.println(String.format("%s=%s", k, v)));
        }

        Map<String, String> headers = new HashMap<>(16);
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }

        try {
            log.info("Http-Headers:\n{}", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(headers));
        } catch (JsonProcessingException e) {
            log.error("Http-Headers:\n{}", "格式化输出失败");
            headers.forEach((k, v) -> System.out.println(String.format("%s=%s", k, v)));
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
