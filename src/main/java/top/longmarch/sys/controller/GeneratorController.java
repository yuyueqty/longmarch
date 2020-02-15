package top.longmarch.sys.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.sys.service.GeneratorService;

import java.util.List;
import java.util.Map;

@Api(value = "代码生成", tags = "代码生成接口")
@RestController
@RequestMapping("/sys/generator")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @ApiOperation(value="数据库表列表")
    @GetMapping("/tableList")
    public Result tableList(
            @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "tableName", required = false) String tableName) {
        tableName = tableName == null || "".equals(tableName) || "null".equals(tableName) ? null : tableName;
        IPage<Map<String, Object>> mapIPage = generatorService.queryList(current, size, tableName);
        return Result.ok().add(mapIPage);
    }

    @ApiOperation(value="表列名")
    @GetMapping("/tableColumns")
    public Result tableColumns(@RequestParam String tableName) {
        List<Map<String, String>> tableColumnsInfo = generatorService.queryColumns(tableName);
        return Result.ok().add(tableColumnsInfo);
    }

}
