package org.lhj.sso.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @author 刘洪君
 * @date 2019/3/29 14:56
 */
public class CustomOauthExceptionSerializer implements WebResponseExceptionTranslator {

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        System.out.println(1);
        return null;
    }
}
