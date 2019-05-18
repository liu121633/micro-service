package org.lhj.sso.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author 刘洪君
 * @date 2019/3/29 14:25
 */
public interface IUserDetailsService  extends UserDetailsService {


    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
