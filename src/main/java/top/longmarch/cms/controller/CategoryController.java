package top.longmarch.cms.controller;


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
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.cms.entity.Category;
import top.longmarch.cms.service.ICategoryService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 文章类目 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "文章类目模块", tags = "文章类目模块接口")
@RestController
@RequestMapping("/cms/category")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation(value="搜索文章类目")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<Category> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(categoryService.page(page, wrapper));
    }

    @ApiOperation(value="查看文章类目")
    @RequiresPermissions("cms:category:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        Category category = categoryService.getById(id);
        return Result.ok().add(category);
    }

    @Log
    @ApiOperation(value="创建文章类目")
    @RequiresPermissions("cms:category:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Category category) {
        log.info("创建文章类目, 入参：{}", category);
        categoryService.save(category);
        return Result.ok().add(category);
    }

    @Log
    @ApiOperation(value="更新文章类目")
    @RequiresPermissions("cms:category:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Category category) {
        log.info("更新文章类目, 入参：{}", category);
        categoryService.updateById(category);
        return Result.ok().add(category);
    }

    @Log
    @ApiOperation(value="删除文章类目")
    @RequiresPermissions("cms:category:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除文章类目, ids={}", ids);
        categoryService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
