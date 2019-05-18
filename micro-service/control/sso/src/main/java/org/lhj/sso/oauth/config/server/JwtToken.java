package org.lhj.sso.oauth.config.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 刘洪君
 * @date 2019/3/26 11:05
 * jwt token 的相关 bean 初始化
 */
@Configuration
public class JwtToken {
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter() {
            /**
             *登录成功返回的的信息中除了token信息 还可以在里面自定义一些信息 用于登录成功后返回
             * @param accessToken {@link OAuth2AccessToken}
             * @param authentication {@link OAuth2Authentication}
             * @return
             */
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                OAuth2Request oAuth2Request = authentication.getOAuth2Request();
                if (oAuth2Request.getGrantType().equals("password")) {
                    String username = authentication.getUserAuthentication().getName();
                    Map<String, Object> additionalInformation = new HashMap<>(16);
                    //记录用户名到token 上面
                    additionalInformation.put("username", username);
                    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                }
                return super.enhance(accessToken, authentication);
            }
        };
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("/jks/oauth2-jwt.jks"), "123456".toCharArray()).getKeyPair("kevin_key");
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
}
