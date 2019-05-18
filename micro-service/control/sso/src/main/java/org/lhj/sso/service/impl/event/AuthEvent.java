package org.lhj.sso.service.impl.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author 刘洪君
 * @date 2019/3/29 10:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("all")
public class AuthEvent {
    private String authType;
    private Map<String, String[]> authParameters;

    public String getAuthParameter(String paramter) {
        String[] values = this.authParameters.get(paramter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }
}
