package org.lhj.sso.service.impl;

import org.lhj.sso.service.IUserDetailsService;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 刘洪君
 * @date 2019/3/11 0:07
 */
@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

    @Resource
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new User(
                userName,
                passwordEncoder.encode("123456"),
                AuthorityUtils.createAuthorityList());
    }
}
