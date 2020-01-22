package ${package.Controller};


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
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import java.util.Arrays;
import java.util.Map;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "${table.comment!}模块", tags = "${table.comment!}模块接口")
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private static final Logger log = LoggerFactory.getLogger(${table.controllerName}.class);
    @Autowired
    private ${table.serviceName} ${table.serviceName?substring(2)?uncap_first};

    @ApiOperation(value="搜索${table.comment!}")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<${entity}> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<${entity}> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(${table.serviceName?substring(2)?uncap_first}.page(page, wrapper));
    }

    @ApiOperation(value="查看${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        ${entity} ${entity?uncap_first} = ${table.serviceName?substring(2)?uncap_first}.getById(id);
        return Result.ok().add(${entity?uncap_first});
    }

    @Log
    @ApiOperation(value="创建${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody ${entity} ${entity?uncap_first}) {
        log.info("创建${table.comment!}, 入参：{}", ${entity?uncap_first});
        ${table.serviceName?substring(2)?uncap_first}.save(${entity?uncap_first});
        return Result.ok().add(${entity?uncap_first});
    }

    @Log
    @ApiOperation(value="更新${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody ${entity} ${entity?uncap_first}) {
        log.info("更新${table.comment!}, 入参：{}", ${entity?uncap_first});
        ${table.serviceName?substring(2)?uncap_first}.updateById(${entity?uncap_first});
        return Result.ok().add(${entity?uncap_first});
    }

    @Log
    @ApiOperation(value="删除${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除${table.comment!}, ids={}", ids);
        ${table.serviceName?substring(2)?uncap_first}.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
</#if>
