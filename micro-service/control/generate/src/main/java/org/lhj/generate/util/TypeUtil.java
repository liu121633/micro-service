package org.lhj.generate.util;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 刘洪君
 * @date 2019/4/23 21:22
 */
public class TypeUtil {
    static HashMap<String, FullyQualifiedJavaType> TYPE_MAP;

    static {
        TYPE_MAP = new HashMap<>();
        TYPE_MAP.put("ARRAY", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("BIGINT", new FullyQualifiedJavaType(Long.class.getName()));
        TYPE_MAP.put("BINARY", new FullyQualifiedJavaType("byte[]"));
        TYPE_MAP.put("BIT", new FullyQualifiedJavaType(Boolean.class.getName()));
        TYPE_MAP.put("BLOB", new FullyQualifiedJavaType("byte[]"));
        TYPE_MAP.put("BOOLEAN", new FullyQualifiedJavaType(Boolean.class.getName()));
        TYPE_MAP.put("CHAR", new FullyQualifiedJavaType(String.class.getName()));
        TYPE_MAP.put("CLOB", new FullyQualifiedJavaType(String.class.getName()));
        TYPE_MAP.put("DATALINK", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("DATE", new FullyQualifiedJavaType(Date.class.getName()));
        TYPE_MAP.put("DECIMAL", new FullyQualifiedJavaType(BigDecimal.class.getName()));
        TYPE_MAP.put("DISTINCT", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("DOUBLE", new FullyQualifiedJavaType(Double.class.getName()));
        TYPE_MAP.put("FLOAT", new FullyQualifiedJavaType(Double.class.getName()));
        TYPE_MAP.put("INTEGER", new FullyQualifiedJavaType(Integer.class.getName()));
        TYPE_MAP.put("DATETIME", new FullyQualifiedJavaType(Date.class.getName()));
        TYPE_MAP.put("INT", new FullyQualifiedJavaType(Integer.class.getName()));
        TYPE_MAP.put("JAVA_OBJECT", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("LONGNVARCHAR", new FullyQualifiedJavaType(String.class.getName()));
        TYPE_MAP.put("LONGVARBINARY", new FullyQualifiedJavaType("byte[]"));
        TYPE_MAP.put("LONGVARCHAR", new FullyQualifiedJavaType(String.class.getName()));
        TYPE_MAP.put("NCHAR", new FullyQualifiedJavaType(String.class.getName()));
        TYPE_MAP.put("NCLOB", new FullyQualifiedJavaType(String.class.getName()));
        TYPE_MAP.put("NVARCHAR", new FullyQualifiedJavaType(String.class.getName()));
        TYPE_MAP.put("NULL", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("NUMERIC", new FullyQualifiedJavaType(BigDecimal.class.getName()));
        TYPE_MAP.put("OTHER", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("REAL", new FullyQualifiedJavaType(Float.class.getName()));
        TYPE_MAP.put("REF", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("SMALLINT", new FullyQualifiedJavaType(Short.class.getName()));
        TYPE_MAP.put("STRUCT", new FullyQualifiedJavaType(Object.class.getName()));
        TYPE_MAP.put("TIME", new FullyQualifiedJavaType(Date.class.getName()));
        TYPE_MAP.put("TIMESTAMP", new FullyQualifiedJavaType(Date.class.getName()));
        TYPE_MAP.put("TINYINT", new FullyQualifiedJavaType(Byte.class.getName()));
        TYPE_MAP.put("VARBINARY", new FullyQualifiedJavaType("byte[]"));
        TYPE_MAP.put("VARCHAR", new FullyQualifiedJavaType(String.class.getName()));
    }

    @SuppressWarnings("all")
    public static FullyQualifiedJavaType get(String str) {
        int i = str.indexOf("(");

        DbType dbType = new DbType();
        if (i > 0) {
            String[] split = str.substring(i + 1, str.length() - 1).split(",");
            int[] ints = stringToInt(split);
            dbType.setTypeName(str.substring(0, i));
            dbType.setSize(ints);
        } else {
            dbType.setTypeName(str);
            int[] ist = {};
            dbType.setSize(ist);
        }

        if ("TINYINT".equals(dbType.getTypeName())) {
            if (dbType.getSize()[0] == 1) {
                return TYPE_MAP.get("BOOLEAN");
            }
        }

        return TYPE_MAP.get(dbType.typeName.toUpperCase());
    }

    @SuppressWarnings("all")
    private static int[] stringToInt(String[] arrs) {
        int[] ints = new int[arrs.length];
        for (int i = 0; i < arrs.length; i++) {
            try {
                ints[i] = Integer.parseInt(arrs[i]);
            } catch (NumberFormatException e) {
            }
        }
        return ints;
    }

}
