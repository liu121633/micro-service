package org.lhj.generate.controller;

import org.lhj.generate.domain.dao.Columns;
import org.lhj.generate.domain.res.ResEntity;
import org.lhj.generate.mapper.ColumnsMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刘洪君
 * @date 2019/4/23 19:38
 */
@RestController
@RequestMapping("/columns")
public class ColumnsController {

    @Resource
    ColumnsMapper columnsMapper;

    @GetMapping("/{tableSchema}/{tableName}")
    public ResEntity<List<Columns>> search(@PathVariable String tableSchema, @PathVariable String tableName) {
        Example example = new Example(Columns.class);
        example.and().andEqualTo("tableSchema", tableSchema);
        example.and().andEqualTo("tableName", tableName);
        return ResEntity.ok(columnsMapper.selectByExample(example));
    }
}
