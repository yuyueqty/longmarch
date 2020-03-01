package ${package.Controller};


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
import top.longmarch.core.common.Constant;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.LmUtils;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import java.util.Arrays;
import java.util.List;
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
 * @author ${author}
 * @since ${date}
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
    private ${table.serviceName} ${table.serviceName?substring(1)?uncap_first};

    @ApiOperation(value = "搜索${table.comment!}")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<${entity}> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<${entity}> wrapper = new LambdaQueryWrapper<>();
        <#list fieldGenerationConditionList as condition>
        <#if condition.queryType?? && condition.queryType == "eq">
        wrapper.eq(LmUtils.isNotBlank(params.get("${condition.propertyName}")), ${table.entityName}::get${condition.propertyName?cap_first}, params.get("${condition.propertyName}"));
        </#if>
        </#list>

        <#assign newList = [] />
        <#list fieldGenerationConditionList as condition>
        <#if condition.queryType?? && condition.queryType == "like">
        <#assign newList = newList + [condition] />
        </#if>
        </#list>
        <#if newList?size == 1>
        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        wrapper.like(LmUtils.isNotBlank(fuzzySearch), ${entity}::get${newList[0].propertyName?cap_first}, fuzzySearch);
        </#if>
        <#if newList?size gt 1>
        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        wrapper.and(LmUtils.isNotBlank(fuzzySearch), p -> p.like(${entity}::get${newList[0].propertyName?cap_first}, fuzzySearch)
        <#list newList as condition>
        <#if condition_index gt 0>
        .or().like(${entity}::get${condition.propertyName?cap_first}, fuzzySearch)<#if !condition_has_next>);</#if>
        <#else>
        </#if>
        </#list>
        </#if>

        <#list fieldGenerationConditionList as condition>
        <#if condition.queryType?? && condition.queryType == "date">
        if (LmUtils.isNotBlank(params.get("${condition.propertyName}"))) {
        List<Object> ${condition.propertyName} = (List<Object>) params.get("${condition.propertyName}");
            wrapper.between(${entity}::get${condition.propertyName?cap_first}, ${condition.propertyName}.get(0), ${condition.propertyName}.get(1));
        }
        </#if>
        </#list>

        <#list fieldGenerationConditionList as condition>
        <#if condition.orderBy?? && condition.orderBy>
        Object ${condition.propertyName} = params.get(Constant.PROP);
        Object ${condition.propertyName}Order = params.get(Constant.ORDER);
        if (LmUtils.isNotBlank(${condition.propertyName}) && LmUtils.isNotBlank(${condition.propertyName}Order)) {
            boolean isAsc = "ascending".equals(${condition.propertyName}Order);
            wrapper.orderBy(true, isAsc, ${entity}::get${condition.propertyName?cap_first});
        }
        </#if>
        </#list>
        return Result.ok().add(${table.serviceName?substring(1)?uncap_first}.page(page, wrapper));
    }

    @ApiOperation(value = "查看${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        ${entity} ${entity?uncap_first} = ${table.serviceName?substring(1)?uncap_first}.getById(id);
        return Result.ok().add(${entity?uncap_first});
    }

    @Log
    @ApiOperation(value = "创建${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody ${entity} ${entity?uncap_first}) {
        log.info("创建${table.comment!}, 入参：{}", ${entity?uncap_first});
        ${table.serviceName?substring(1)?uncap_first}.save(${entity?uncap_first});
        return Result.ok().add(${entity?uncap_first});
    }

    @Log
    @ApiOperation(value = "更新${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody ${entity} ${entity?uncap_first}) {
        log.info("更新${table.comment!}, 入参：{}", ${entity?uncap_first});
        ${table.serviceName?substring(1)?uncap_first}.updateById(${entity?uncap_first});
        return Result.ok().add(${entity?uncap_first});
    }

    @Log
    @ApiOperation(value = "删除${table.comment!}")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除${table.comment!}, ids={}", ids);
        ${table.serviceName?substring(1)?uncap_first}.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改${table.comment!}状态")
    @RequiresPermissions("${package.ModuleName}:${entity?uncap_first}:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改${table.comment!}状态, 入参：{}", changeStatusDTO);
        ${entity} ${entity?uncap_first} = new ${entity}();
        BeanUtils.copyProperties(changeStatusDTO, ${entity?uncap_first});
        ${table.serviceName?substring(1)?uncap_first}.updateById(${entity?uncap_first});
        return Result.ok().add(${entity?uncap_first});
    }

}
</#if>
