package org.lhj.sso.oauth.config.resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 刘洪君
 * @date 2019/3/26 10:06
 */

@Configuration
@EnableResourceServer
public class ResourceServerConfig
        extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                //白名单
                .antMatchers(
                        "/swagger-ui.html/**"
                        , "/favicon.ico"
                        , "/webjars/**"
                        , "/swagger-resources/**"
                        , "/v2/**"
                        , "/users/register"
                        , "/"
                        , "/csrf"
                )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable();
    }

    @Autowired
    Environment environment;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(getResourceId());
    }

    /**
     * 获取当前资源服务器 定义的的资源id
     *
     * @return 资源id
     */
    private String getResourceId() {
        String resourceId = environment.getProperty("security.oauth2.resource.id");
        if (!StringUtils.isNotBlank(resourceId)) {
            resourceId = environment.getProperty("spring.application.name");
        }
        if (!StringUtils.isNotBlank(resourceId)) {
            throw new RuntimeException("请为当前服务设置 资源id" +
                    "你可以设置如下参数" +
                    "security.oauth2.resource.id" +
                    "or" +
                    "spring.application.name"
            );
        }

        return resourceId;
    }
}