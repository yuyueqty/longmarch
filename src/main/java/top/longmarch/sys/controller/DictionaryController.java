package top.longmarch.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.enums.StatusEnum;
import top.longmarch.core.utils.LmUtils;
import top.longmarch.sys.entity.Dictionary;
import top.longmarch.sys.entity.dto.ChangeStatusDTO;
import top.longmarch.sys.service.IDictionaryService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "搜索字典信息")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        params = PageFactory.buildMap(params);
        IPage<Dictionary> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<Dictionary> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件

        Object code = params.get("code");
        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        Object prop = params.get(Constant.PROP);
        Object order = params.get(Constant.ORDER);
        wrapper.eq(LmUtils.isNotBlank(code), Dictionary::getCode, code);
        wrapper.like(LmUtils.isNotBlank(fuzzySearch), Dictionary::getLabel, fuzzySearch);
        boolean condition = LmUtils.isNotBlank(prop) && LmUtils.isNotBlank(order);
        boolean isAsc = "ascending".equals(order);
        wrapper.orderBy(condition, isAsc, Dictionary::getCode);
        return Result.ok().add(ictionaryService.page(page, wrapper));
    }

    /**
     * any_value mysql group by 的一个bug
     * https://www.cnblogs.com/kenshinobiy/p/9580701.html
     * @return
     */
    @ApiOperation(value = "字典编码")
    @GetMapping("/loadDictionaryCode")
    public Result loadDictionaryCode() {
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("status", StatusEnum.YES.getValue());
        wrapper.select("any_value(description) as label", "code as value");
        wrapper.groupBy("code");
        List<Map<String, Object>> dictionaryList = ictionaryService.listMaps(wrapper);
        return Result.ok().add(dictionaryList);
    }

    @ApiOperation(value = "查看字典信息")
    @RequiresPermissions("sys:dictionary:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        Dictionary dictionary = ictionaryService.getById(id);
        return Result.ok().add(dictionary);
    }

    @Log
    @ApiOperation(value="修改字典状态")
    @RequiresPermissions("sys:dictionary:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改字典状态, 入参：{}", changeStatusDTO);
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(changeStatusDTO, dictionary);
        ictionaryService.updateById(dictionary);
        return Result.ok().add(dictionary);
    }

    @Log
    @ApiOperation(value = "创建字典信息")
    @RequiresPermissions("sys:dictionary:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Dictionary dictionary) {
        log.info("创建字典信息, 入参：{}", dictionary);
        dictionary.setCode(String.format(dictionary.getCode(), "_dict"));
        ictionaryService.saveDictionary(dictionary);
        return Result.ok().add(dictionary);
    }

    @Log
    @ApiOperation(value = "更新字典信息")
    @RequiresPermissions("sys:dictionary:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Dictionary dictionary) {
        log.info("更新字典信息, 入参：{}", dictionary);
        ictionaryService.updateDictionary(dictionary);
        return Result.ok().add(dictionary);
    }

    @Log
    @ApiOperation(value = "删除字典信息")
    @RequiresPermissions("sys:dictionary:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除字典信息, ids={}", ids);
        ictionaryService.removeDictionary(Arrays.asList(ids));
        return Result.ok();
    }

}
