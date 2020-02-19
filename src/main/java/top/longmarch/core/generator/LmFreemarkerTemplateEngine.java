package top.longmarch.core.generator;

import cn.hutool.db.Entity;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import top.longmarch.sys.entity.Generator;

import java.util.List;
import java.util.Map;

public class LmFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    private List<Generator> fieldGenerationConditionList;

    public LmFreemarkerTemplateEngine(List<Generator> fieldGenerationConditionList) {
        this.fieldGenerationConditionList = fieldGenerationConditionList;
    }

    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> map = super.getObjectMap(tableInfo);
        map.put("fieldGenerationConditionList", fieldGenerationConditionList);
        return map;
    }

}
