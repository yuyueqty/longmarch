package top.longmarch.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GeneratorDao {

    IPage<Map<String, Object>> queryList(IPage page, @Param("tableName") String tableName);

    List<Map<String, String>> queryColumns(String tableName);

}