package org.lhj.generate.domain.model;

import lombok.Data;

/**
 * @author 刘洪君
 * @date 2019/4/24 1:35
 */
@Data
public class GenerateModel {
    private String fieldName;
    private String columnComment;
    private String javaType;
}
