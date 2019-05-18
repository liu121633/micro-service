package org.lhj.sso.service.impl;

import org.lhj.sso.service.IUserDetailsService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author 刘洪君
 * @date 2019/3/29 14:23
 */
public class DaoAuthenticationProviderImpl extends AbstractUserDetailsAuthenticationProvider {

    private IUserDetailsService userDetailsService;

    private PasswordEncoder passwordEncoder;

    public DaoAuthenticationProviderImpl(IUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
         if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
             throw new BadCredentialsException("密码不匹配");
         }
        return userDetails;
    }
}
