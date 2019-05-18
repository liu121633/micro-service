package org.lhj.generate.domain.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 刘洪君
 * @date 2019/4/23 0:24
 */
@Data
public class TablesSearchModel {
    Set<String> tableSchemas = new HashSet<>();
    private String tableName;
    private String tableComment;
}
