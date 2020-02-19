package top.longmarch.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import top.longmarch.sys.entity.Generator;

import java.util.List;
import java.util.Map;

public interface GeneratorService {

    IPage<Map<String, Object>> queryList(long current, long size, String tableName);

    List<Generator> queryColumns(String tableName);

    void saveGenerator(Map<String, Object> params);

}
