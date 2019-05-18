package org.lhj.generate.domain.res;

import com.github.pagehelper.Page;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author 刘洪君
 * @date 2019/4/22 21:31
 */

@Data
public class ResEntity<T> {
    HttpStatus status;
    T body;

    public static <T> ResEntity<T> ok() {
        ResEntity<T> res = new ResEntity<>();
        res.setStatus(HttpStatus.OK);
        return res;
    }

    public static <T> ResEntity<T> ok(T t) {
        ResEntity<T> res = new ResEntity<>();
        res.setStatus(HttpStatus.OK);
        res.setBody(t);
        return res;
    }

    public static <T> ResEntity<ResPages<T>> pages(Page<T> t) {
        ResEntity<ResPages<T>> res = new ResEntity<>();
        res.setStatus(HttpStatus.OK);
        res.setBody(new ResPages<>(t));
        return res;
    }
}
