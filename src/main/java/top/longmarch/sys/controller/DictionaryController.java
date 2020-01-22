package top.longmarch.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.sys.entity.Dictionary;
import top.longmarch.sys.entity.Role;
import top.longmarch.sys.service.IDictionaryService;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 字典信息 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "字典信息模块", tags = "字典信息模块接口")
@RestController
@RequestMapping("/sys/dictionary")
public class DictionaryController {

    private static final Logger log = LoggerFactory.getLogger(DictionaryController.class);
    @Autowired
    private IDictionaryService ictionaryService;

    @ApiOperation(value="搜索字典信息")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<Dictionary> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        wrapper.like(Objects.nonNull(params.get(Constant.FUZZY_SEARCH)), Dictionary::getCode, params.get(Constant.FUZZY_SEARCH));
        return Result.ok().add(ictionaryService.page(page, wrapper));
    }

    @ApiOperation(value="查看字典信息")
    @RequiresPermissions("sys:dictionary:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        Dictionary dictionary = ictionaryService.getById(id);
        return Result.ok().add(dictionary);
    }

    @Log
    @ApiOperation(value="创建字典信息")
    @RequiresPermissions("sys:dictionary:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Dictionary dictionary) {
        log.info("创建字典信息, 入参：{}", dictionary);
        dictionary.setCode(String.format(dictionary.getCode(), "_dict"));
        ictionaryService.saveDictionary(dictionary);
        return Result.ok().add(dictionary);
    }

    @Log
    @ApiOperation(value="更新字典信息")
    @RequiresPermissions("sys:dictionary:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Dictionary dictionary) {
        log.info("更新字典信息, 入参：{}", dictionary);
        ictionaryService.updateDictionary(dictionary);
        return Result.ok().add(dictionary);
    }

    @Log
    @ApiOperation(value="删除字典信息")
    @RequiresPermissions("sys:dictionary:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除字典信息, ids={}", ids);
        ictionaryService.removeDictionary(Arrays.asList(ids));
        return Result.ok();
    }

}
