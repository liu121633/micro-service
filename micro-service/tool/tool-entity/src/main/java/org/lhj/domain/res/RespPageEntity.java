package org.lhj.domain.res;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @author 刘洪君
 * @date 2019/4/21 20:58
 */
@Data
public class RespPageEntity<T> extends RespEntity<List<T>> {

    private Integer pageSize;

    private Integer pageNum;

    private Long total;

    private Integer pages;


    public static <T> RespPageEntity<T> of(Page<T> page) {
        RespPageEntity<T> resp = new RespPageEntity<>();
        resp.setStatus(RespStatus.OK);
        resp.setPageSize(page.getPageSize());
        resp.setTotal(page.getTotal());
        resp.setPageNum(page.getPageNum());
        resp.setPages(page.getPages());
        resp.setData(page.getResult());
        return resp;
    }
}
