package top.longmarch.sys.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.sys.dao.GeneratorDao;
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
    public List<Map<String, String>> queryColumns(String tableName) {
        return generatorDao.queryColumns(tableName);
    }

}
