package org.lhj.generate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 刘洪君
 * @date 2019/4/23 22:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
class DbType {
    String typeName;
    int[] size;
}
