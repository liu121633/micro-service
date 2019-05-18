package org.lhj.sso.oauth.config.server;

import org.lhj.sso.service.IUserDetailsService;
import org.lhj.sso.service.impl.DaoAuthenticationProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * @author 刘洪君
 * @date 2019/3/11 1:41
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private IUserDetailsService userService;
    private PasswordEncoder passwordEncoder;
    private AuthenticationProvider authenticationProvider;

    public SecurityConfig(IUserDetailsService userService, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authenticationProvider = new DaoAuthenticationProviderImpl(userService, passwordEncoder);
    }


    /**
     * 用户认证
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.authenticationProvider(authenticationProvider)
        //        .userDetailsService(userService)
        //        .passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/swagger-ui.html/**"
                        , "/favicon.ico"
                        , "/webjars/**"
                        , "/swagger-resources/**"
                        , "/v2/**"
                        , "/users/register"
                        , "/"
                        , "/csrf"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

}