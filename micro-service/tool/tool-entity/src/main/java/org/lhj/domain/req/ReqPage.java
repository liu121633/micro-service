package org.lhj.domain.req;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 刘洪君
 * @date 2019/4/21 21:04
 */
@Data
public class ReqPage<T> {

    @NotNull
    Integer pageNum;
    @NotNull
    Integer pageSize;
    T where;

}
