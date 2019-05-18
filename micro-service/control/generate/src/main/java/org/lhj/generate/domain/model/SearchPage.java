package org.lhj.generate.domain.model;

import lombok.Data;

/**
 * @author 刘洪君
 * @date 2019/4/22 23:26
 */
@Data
public class SearchPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private OrderByModel orderBy;
    private T where;
}
