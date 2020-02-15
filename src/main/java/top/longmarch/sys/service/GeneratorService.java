package top.longmarch.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

public interface GeneratorService {

    IPage<Map<String, Object>> queryList(long current, long size, String tableName);

    List<Map<String, String>> queryColumns(String tableName);

}
