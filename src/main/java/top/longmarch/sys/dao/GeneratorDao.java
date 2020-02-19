package top.longmarch.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import top.longmarch.sys.entity.Generator;

import java.util.List;
import java.util.Map;

public interface GeneratorDao {

    IPage<Map<String, Object>> queryList(IPage page, @Param("tableName") String tableName);

    List<Generator> queryColumns(String tableName);

    void updateByTableNameAndColumnName(@Param("p") Map<String, Object> params);

    void insertGenerator(@Param("p") Map<String, Object> params);

    int selectCount(String tableName, String columnName);

}