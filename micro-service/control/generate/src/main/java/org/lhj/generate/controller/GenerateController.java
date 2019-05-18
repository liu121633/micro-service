package org.lhj.generate.controller;

import org.lhj.generate.util.GenerateUtil;
import org.lhj.generate.domain.model.GenerateModel;
import org.lhj.generate.domain.res.ResEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 刘洪君
 * @date 2019/4/24 1:33
 */
@RestController
@RequestMapping("/generate")
public class GenerateController {

    @PostMapping("/{className}")
    public ResEntity<String> test(@PathVariable String className, @Valid @RequestBody List<GenerateModel> generateModels) {
        return ResEntity.ok(new GenerateUtil(className, generateModels).get());
    }

}
