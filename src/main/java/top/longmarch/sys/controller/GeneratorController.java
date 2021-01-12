package top.longmarch.sys.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ZipUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.Result;
import top.longmarch.core.generator.CodeGeneratorOpen;
import top.longmarch.sys.entity.Generator;
import top.longmarch.sys.service.GeneratorService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Api(value = "代码生成", tags = "代码生成接口")
@RestController
@RequestMapping("/sys/generator")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @ApiOperation(value = "数据库表列表")
    @GetMapping("/tableList")
    public Result tableList(
            @RequestParam(name = "current", required = false, defaultValue = "1") Integer current,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "tableName", required = false) String tableName) {
        tableName = tableName == null || "".equals(tableName) || "null".equals(tableName) ? null : tableName;
        IPage<Map<String, Object>> mapIPage = generatorService.queryTables(current, size, tableName);
        return Result.ok().add(mapIPage);
    }

    @ApiOperation(value = "表列名")
    @RequiresPermissions("sys:generator:columns")
    @GetMapping("/tableColumns")
    public Result tableColumns(@RequestParam String tableName) {
        List<Generator> tableColumnsInfo = generatorService.queryColumns(tableName);
        return Result.ok().add(tableColumnsInfo);
    }

    @ApiOperation(value = "创建规则")
    @RequiresPermissions("sys:generator:save")
    @PostMapping("/saveGenerator")
    public Result create(@RequestBody Generator generator) {
        List<Generator> generators = new ArrayList<>();
        generators.add(generator);
        generatorService.saveOrUpdateBatchGenerator(generators);
        return Result.ok();
    }

    @ApiOperation(value = "批量创建规则")
    @RequiresPermissions("sys:generator:batch:save")
    @PostMapping("/batchSaveGenerator")
    public Result batchSaveGenerator(@RequestBody List<Generator> generators) {
        generatorService.saveOrUpdateBatchGenerator(generators);
        return Result.ok();
    }

    @ApiOperation(value = "下载文件")
    @RequiresPermissions("sys:generator:download")
    @GetMapping("/download")
    public ResponseEntity download(@RequestParam String moduleName, @RequestParam String tableName) {
        FileUtil.del(Constant.PROJECT_PATH);
        generatorService.runGenerator(moduleName, Arrays.asList(tableName));
        File file = ZipUtil.zip(Constant.PROJECT_PATH);

        if (!file.exists()) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        //chrome浏览器下载文件可能出现：ERR_RESPONSE_HEADERS_MULTIPLE_CONTENT_DISPOSITION，
        //产生原因：可能是因为文件名中带有英文半角逗号,
        //解决办法：确保 filename 参数使用双引号包裹[1]
        headers.add("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }

}
