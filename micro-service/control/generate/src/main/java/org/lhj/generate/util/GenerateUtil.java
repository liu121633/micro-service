package org.lhj.generate.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.lhj.generate.domain.model.GenerateModel;

import java.util.*;
import java.util.function.Function;

/**
 * @author 刘洪君
 * @date 2019/4/24 2:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenerateUtil {
    List<GenerateModel> generateModels;

    String className;

    public GenerateUtil(String className, List<GenerateModel> generateModels) {
        this.generateModels = generateModels;
        this.className = className;
    }

    private Function<String, String> generateImpl = packageFName -> String.format("import %s;\n", packageFName);

    /**
     * @return
     */
    public String get() {
        Set<String> impl = new HashSet<>();
        Set<String> classAoon = new HashSet<>();
        classAoon.add("@Data\n");
        classAoon.add("@AllArgsConstructor\n");
        classAoon.add("@NoArgsConstructor\n");
        impl.add(generateImpl.apply("lombok.*"));

        StringBuffer body = new StringBuffer();
        generateModels.forEach(item -> {
            impl.add(generateImpl.apply(item.getJavaType()));
            body.append("\n");
            StringBuilder doc = new StringBuilder();
            doc.append("\t/**\n");
            doc.append("\t *").append(item.getColumnComment()).append("\n");
            doc.append("\t */\n");
            body.append(doc);
            StringBuilder fileDefinition = new StringBuilder();
            String[] split = item.getJavaType().split("\\.");
            fileDefinition
                    .append("\t")
                    .append("private\t")
                    .append(Arrays.asList(split).get(split.length - 1)).append("\t")
                    .append(item.getFieldName()).append(";").append("\t");
            body.append(fileDefinition);
            body.append("\n");
        });
        StringBuffer classValue = new StringBuffer();
        impl.forEach(classValue::append);
        classValue.append("\n").append("\n").append("\n");
        classValue.append("/**").append("\n");
        classValue.append(" * @author 刘洪君").append("\n");
        classValue.append(" * @date ").append(DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss")).append("\n");
        classValue.append(" */").append("\n");
        classAoon.forEach(classValue::append);
        classValue.append("public\t").append("class\t").append(className).append("{");
        classValue.append(body);
        classValue.append("}");
        return String.valueOf(classValue);
    }
}
