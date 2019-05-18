package org.lhj.generate.controller;

import org.lhj.generate.domain.dao.Schemata;
import org.lhj.generate.domain.res.ResEntity;
import org.lhj.generate.mapper.SchemataMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刘洪君
 * @date 2019/4/22 21:41
 */
@RestController
@RequestMapping("/schemata")
public class SchemataController {

    @Resource
    SchemataMapper schemataMapper;

    @GetMapping("/search")
    public ResEntity<List<Schemata>> search() {
        Example example = new Example(Schemata.class);
        return ResEntity.ok(schemataMapper.selectByExample(example));
    }
}
