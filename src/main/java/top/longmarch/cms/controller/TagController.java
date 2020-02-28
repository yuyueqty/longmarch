package top.longmarch.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.cms.entity.Tag;
import top.longmarch.cms.service.ITagService;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.LmUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Api(value = "标签模块", tags = "标签模块接口")
@RestController
@RequestMapping("/cms/tag")
public class TagController {

    private static final Logger log = LoggerFactory.getLogger(TagController.class);
    @Autowired
    private ITagService tagService;

    @ApiOperation(value = "搜索标签")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<Tag> page = PageFactory.getInstance(params);
        QueryWrapper<Tag> wrapper = new QueryWrapper<>();
        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        wrapper.lambda().like(LmUtils.isNotBlank(fuzzySearch), Tag::getTagName, fuzzySearch);
        Object prop = params.get(Constant.PROP);
        Object order = params.get(Constant.ORDER);
        if (LmUtils.isNotBlank(prop) && LmUtils.isNotBlank(order)) {
            boolean isAsc = "ascending".equals(order);
            wrapper.orderBy(true, isAsc, prop.toString());
        }
        return Result.ok().add(tagService.page(page, wrapper));
    }

    @ApiOperation(value = "查看标签")
    @RequiresPermissions("cms:tag:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        Tag tag = tagService.getById(id);
        return Result.ok().add(tag);
    }

    @Log
    @ApiOperation(value = "创建标签")
    @RequiresPermissions("cms:tag:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Tag tag) {
        log.info("创建标签, 入参：{}", tag);
        tagService.saveTag(tag);
        return Result.ok().add(tag);
    }

    @Log
    @ApiOperation(value = "更新标签")
    @RequiresPermissions("cms:tag:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Tag tag) {
        log.info("更新标签, 入参：{}", tag);
        tagService.updateById(tag);
        return Result.ok().add(tag);
    }

    @Log
    @ApiOperation(value = "删除标签")
    @RequiresPermissions("cms:tag:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除标签, ids={}", ids);
        tagService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
