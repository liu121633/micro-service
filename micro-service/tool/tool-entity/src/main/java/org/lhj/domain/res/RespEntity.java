package org.lhj.domain.res;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘洪君
 * @date 2019/4/21 17:00
 */

@Data
public class RespEntity<T> implements Serializable {

    private RespStatus status;

    private T data;

    private String message;

    public static <T> RespEntity<T> ok() {
        RespEntity<T> resp = new RespEntity<>();
        resp.setMessage("message");
        resp.setStatus(RespStatus.OK);
        return resp;
    }

    public static <T> RespEntity<T> of(T t) {
        RespEntity<T> resp = new RespEntity<>();
        resp.setMessage("message");
        resp.setStatus(RespStatus.OK);
        resp.setData(t);
        return resp;
    }

    public static <T> RespEntity<T> error(RespStatus respStatus, String message) {
        RespEntity<T> resp = new RespEntity<>();
        resp.setMessage("message");
        resp.setStatus(respStatus);
        resp.setMessage(message);
        return resp;
    }

}
