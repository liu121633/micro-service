package org.lhj.sso.oauth.config.server;

import org.lhj.sso.service.IUserDetailsService;
import org.lhj.sso.service.impl.CustomOauthExceptionSerializer;
import org.lhj.sso.service.impl.IntegrationAuthenticationFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author 刘洪君
 * @date 2019/3/26 9:50
 */
@Configuration
@EnableAuthorizationServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    AuthenticationManager authenticationManager;

    @Resource
    IUserDetailsService userDetailsService;

    @Resource
    ApplicationContext applicationContext;

    @Resource
    TokenStore tokenStore;

    @Resource
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Resource
    private DataSource dataSource;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //token处理
                .accessTokenConverter(jwtAccessTokenConverter)
                //登录业务
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices())
                //账号查找
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //密码处理器
        security.passwordEncoder(passwordEncoder)
                //开启表单验证
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
//        actuator
                .addTokenEndpointAuthenticationFilter(new IntegrationAuthenticationFilter(applicationContext));
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client_2")
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("server")
                .authorities("oauth2")
                .secret(passwordEncoder.encode("123"));

//        clients.jdbc(dataSource);
    }
}