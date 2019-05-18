package org.lhj.generate.domain.model;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.lhj.generate.util.ToolUtil;

/**
 * @author 刘洪君
 * @date 2019/4/22 23:28
 */
@Data
public class OrderByModel {
    String prop;
    String order;

    public String getStr() {
        if (StringUtils.isBlank(prop) || StringUtils.isBlank(order)) {
            return null;
        }
        if ("ascending".equals(order)) {
            order = "ASC";
        }

        if ("descending".equals(order)) {
            order = "DESC";
        }
        if (StringUtils.isNotBlank(prop)) {
            this.prop = ToolUtil.humpToLine(prop);
        }

        return prop + " " + order;
    }
}

