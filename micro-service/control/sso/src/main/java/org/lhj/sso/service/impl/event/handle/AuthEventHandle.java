package org.lhj.sso.service.impl.event.handle;

import org.lhj.sso.service.impl.event.AuthEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author 刘洪君
 * @date 2019/3/29 10:54
 */
@Component
public class AuthEventHandle {

    @EventListener(condition = "#event.authType.equals('phone')")
    public void handle(AuthEvent event) {
        String phoneCaptcha = event.getAuthParameter("phoneCaptcha");
        //TODO 验证手机验证码
    }

    @EventListener(condition = "#event.authType.equals('password')")
    public void handle2(AuthEvent event) {
        String imageCaptcha = event.getAuthParameter("imageCaptcha");
        //TODO 验证图像验证码
    }

}