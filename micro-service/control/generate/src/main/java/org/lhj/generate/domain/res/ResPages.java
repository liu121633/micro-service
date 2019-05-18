package org.lhj.generate.domain.res;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * @author 刘洪君
 * @date 2019/4/22 23:19
 */
@Data
public class ResPages<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer startRow;
    private Integer endRow;
    private Integer pages;
    private Long total;
    private List<T> data;

    public ResPages(Page<T> page) {
        this.startRow = page.getStartRow();
        this.endRow = page.getEndRow();
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.pages = page.getPages();
        this.total = page.getTotal();
        this.data = page.getResult();
    }
}
