package org.lhj.domain.res;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author 刘洪君
 * @date 2019/4/21 17:02
 */
public enum RespStatus {


    /**
     * 成功
     */
    OK(1),
    ERROR(99);

    Integer code;

    @JsonValue
    public Integer getCode() {
        return code;
    }

    RespStatus(Integer code) {
        this.code = code;
    }
}
