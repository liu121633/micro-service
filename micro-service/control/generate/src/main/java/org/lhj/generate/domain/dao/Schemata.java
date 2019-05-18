package org.lhj.generate.domain.dao;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author 刘洪君
 * @date 2019/4/22 21:35
 */
@Data
@Table(schema = "information_schema", name = "schemata")
public class Schemata {
    private String catalogName;
    private String schemaName;
    private String defaultCharacterSetName;
    private String defaultCollationName;
    private String sqlPath;
}
