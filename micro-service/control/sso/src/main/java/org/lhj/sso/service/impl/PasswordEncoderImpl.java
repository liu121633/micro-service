package org.lhj.sso.service.impl;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author 刘洪君
 * @date 2019/3/26 10:31
 */
@Service
public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return Base64.encodeBase64String(charSequence.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String encode = encode(charSequence);
        boolean equals = encode.equals(s);
        return equals;
    }
}
