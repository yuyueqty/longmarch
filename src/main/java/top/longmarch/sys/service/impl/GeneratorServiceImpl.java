package top.longmarch.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.sys.dao.GeneratorDao;
import top.longmarch.sys.entity.Generator;
import top.longmarch.sys.service.GeneratorService;

import java.util.List;
import java.util.Map;

@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private GeneratorDao generatorDao;

    @Override
    public IPage<Map<String, Object>> queryList(long current, long size, String tableName) {
        IPage page = new Page(current, size);
        return generatorDao.queryList(page, tableName);
    }

    @Override
    public List<Generator> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }

    @Override
    public void saveGenerator(Map<String, Object> params) {
        String tableName = params.get("tableName") + "";
        String columnName = params.get("columnName") + "";
        if (StrUtil.isNotBlank(tableName) && StrUtil.isNotBlank(columnName)) {
            int count = generatorDao.selectCount(tableName, columnName);
            if (count > 0) {
                generatorDao.updateByTableNameAndColumnName(params);
            } else {
                params.put("propertyName", StrUtil.toCamelCase(columnName));
                generatorDao.insertGenerator(params);
            }
        } else {
            params.put("propertyName", StrUtil.toCamelCase(columnName));
            generatorDao.insertGenerator(params);
        }
    }

}
