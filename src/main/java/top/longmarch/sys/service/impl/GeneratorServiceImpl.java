package top.longmarch.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.common.Constant;
import top.longmarch.core.generator.CodeGeneratorOpen;
import top.longmarch.sys.dao.GeneratorDao;
import top.longmarch.sys.entity.Generator;
import top.longmarch.sys.service.GeneratorService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GeneratorServiceImpl extends ServiceImpl<GeneratorDao, Generator> implements GeneratorService {

    @Autowired
    private GeneratorDao generatorDao;

    @Override
    public IPage<Map<String, Object>> queryTables(long current, long size, String tableName) {
        IPage page = new Page(current, size);
        return generatorDao.queryTables(page, tableName);
    }

    @Override
    public List<Generator> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }

    @Override
    public void runGenerator(String moduleName, List<String> tableNameList) {
        CodeGeneratorOpen codeGeneratorOpen = new CodeGeneratorOpen();
        for (String tableName : tableNameList) {
            List<Generator> generatorList = generatorDao.selectList(new LambdaQueryWrapper<Generator>().eq(Generator::getTableName, tableName));
            if (generatorList != null && generatorList.size() > 0) {
                codeGeneratorOpen.setProjectPath(String.format("%s/%s", Constant.PROJECT_PATH, tableName));
                codeGeneratorOpen.buildAutoGenerator(moduleName, tableName, generatorList).execute();
            } else {
                throw new RuntimeException("请先完成自动生成配置信息");
            }
        }
    }

    @Override
    public void saveOrUpdateBatchGenerator(List<Generator> generators) {
        if (!CollectionUtil.isEmpty(generators)) {
            List<Generator> collect = generators.stream().map(generator -> {
                // 根据数据库字段生成类的属性名称
                generator.setPropertyName(StrUtil.toCamelCase(generator.getColumnName()));
                // 页面显示为空默认使用数据库字段描述
                if (StrUtil.isBlank(generator.getRemark())) {
                    generator.setRemark(generator.getComment());
                }
                return generator;
            }).collect(Collectors.toList());
            this.saveOrUpdateBatch(generators);
        }
    }

}
