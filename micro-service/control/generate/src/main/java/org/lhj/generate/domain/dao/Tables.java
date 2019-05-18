package org.lhj.generate.domain.dao;

import lombok.Data;

import javax.persistence.Table;

/**
 * @author 刘洪君
 * @date 2019/4/22 21:19
 */
@Data
@Table(schema = "information_schema", name = "tables")
public class Tables {
    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String tableType;
    private String engine;
    private String version;
    private String rowFormat;
    private String tableRows;
    private String avgRowLength;
    private String dataLength;
    private String maxDataLength;
    private String indexLength;
    private String dataFree;
    private String autoIncrement;
    private String createTime;
    private String updateTime;
    private String checkTime;
    private String tableCollation;
    private String checksum;
    private String createOptions;
    private String tableComment;
}
