package org.lhj.generate.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.lhj.generate.domain.dao.Tables;
import org.lhj.generate.domain.model.SearchPage;
import org.lhj.generate.domain.model.TablesSearchModel;
import org.lhj.generate.domain.res.ResEntity;
import org.lhj.generate.domain.res.ResPages;
import org.lhj.generate.mapper.TablesMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Set;

/**
 * @author 刘洪君
 * @date 2019/4/22 21:29
 */
@RestController
@RequestMapping("/tables")
public class TablesController {

    @Resource
    TablesMapper tablesMapper;

    @PostMapping("/search")
    public ResEntity<ResPages<Tables>> search(@RequestBody @Valid SearchPage<TablesSearchModel> pageModel) {
        Example example = new Example(Tables.class);
        Set<String> schematas = pageModel.getWhere().getTableSchemas();
        if (schematas.size() > 0) {
            example.and().andIn("tableSchema", schematas);
        }
        String tableComment = pageModel.getWhere().getTableComment();
        if (StringUtils.isNotBlank(tableComment)) {
            example.and().andLike("tableComment", "%" + tableComment + "%");
        }
        String tableName = pageModel.getWhere().getTableName();
        if (StringUtils.isNotBlank(tableName)) {
            example.and().andLike("tableName", "%" + tableName + "%");
        }
        String orderBy = pageModel.getOrderBy().getStr();
        Page<Tables> page = PageHelper.startPage(pageModel.getPageNum(), pageModel.getPageSize(), orderBy);
        tablesMapper.selectByExample(example);
        return ResEntity.pages(page);
    }
}
