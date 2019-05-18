package org.lhj.generate.domain.dao;

import lombok.Data;
import org.lhj.generate.util.ToolUtil;
import org.lhj.generate.util.TypeUtil;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Objects;

/**
 * @author 刘洪君
 * @date 2019/4/23 19:33
 */
@Data
@Table(schema = "information_schema", name = "columns")
public class Columns {

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String columnName;
    private String ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String characterMaximumLength;
    private String characterOctetLength;
    private String numericPrecision;
    private String numericScale;
    private String datetimePrecision;
    private String characterSetName;
    private String collationName;
    private String columnType;
    private String columnKey;
    private String extra;
    private String privileges;
    private String columnComment;
    private String generationExpression;


    @Transient
    private String fieldName;
    @Transient
    private String javaType;

    public void setColumnName(String columnName) {
        this.fieldName = ToolUtil.lineToHump(columnName);
        this.columnName = columnName;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
        FullyQualifiedJavaType fullyQualifiedJavaType = TypeUtil.get(columnType.toUpperCase());
        if (!Objects.isNull(fullyQualifiedJavaType)) {
            this.javaType=fullyQualifiedJavaType.getFullyQualifiedName();
        }
    }
}
