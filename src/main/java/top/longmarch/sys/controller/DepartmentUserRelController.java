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
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.sys.entity.DepartmentUserRel;
import top.longmarch.sys.service.IDepartmentUserRelService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "模块", tags = "模块接口")
@RestController
@RequestMapping("/sys/department-user-rel")
public class DepartmentUserRelController {

    private static final Logger log = LoggerFactory.getLogger(DepartmentUserRelController.class);
    @Autowired
    private IDepartmentUserRelService departmentUserRelService;

    @ApiOperation(value="搜索")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<DepartmentUserRel> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<DepartmentUserRel> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(departmentUserRelService.page(page, wrapper));
    }

    @ApiOperation(value="查看")
    @RequiresPermissions("sys:departmentUserRel:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        DepartmentUserRel departmentUserRel = departmentUserRelService.getById(id);
        return Result.ok().add(departmentUserRel);
    }

    @Log
    @ApiOperation(value="创建")
    @RequiresPermissions("sys:departmentUserRel:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody DepartmentUserRel departmentUserRel) {
        log.info("创建, 入参：{}", departmentUserRel);
        departmentUserRelService.save(departmentUserRel);
        return Result.ok().add(departmentUserRel);
    }

    @Log
    @ApiOperation(value="更新")
    @RequiresPermissions("sys:departmentUserRel:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody DepartmentUserRel departmentUserRel) {
        log.info("更新, 入参：{}", departmentUserRel);
        departmentUserRelService.updateById(departmentUserRel);
        return Result.ok().add(departmentUserRel);
    }

    @Log
    @ApiOperation(value="删除")
    @RequiresPermissions("sys:departmentUserRel:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除, ids={}", ids);
        departmentUserRelService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
