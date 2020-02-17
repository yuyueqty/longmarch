package top.longmarch;

import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.List;
import java.util.Map;

public class LmFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    private List<Map<String, Object>> fieldGenerationConditionList;

    public LmFreemarkerTemplateEngine(List<Map<String, Object>> fieldGenerationConditionList) {
        this.fieldGenerationConditionList = fieldGenerationConditionList;
    }

    @Override
    public Map<String, Object> getObjectMap(TableInfo tableInfo) {
        Map<String, Object> map = super.getObjectMap(tableInfo);
        map.put("fieldGenerationConditionList", fieldGenerationConditionList);
        return map;
    }

}
