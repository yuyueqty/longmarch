package top.longmarch.wx.controller;


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
import top.longmarch.wx.entity.GzhTag;
import top.longmarch.wx.service.IGzhTagService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 微信公众号标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-05-12
*/
@Api(value = "微信公众号标签模块", tags = "微信公众号标签模块接口")
@RestController
@RequestMapping("/wx/gzh-tag")
public class GzhTagController {

    private static final Logger log = LoggerFactory.getLogger(GzhTagController.class);
    @Autowired
    private IGzhTagService gzhTagService;

    @ApiOperation(value = "搜索微信公众号标签")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhTag> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<GzhTag> wrapper = new LambdaQueryWrapper<>();



        return Result.ok().add(gzhTagService.page(page, wrapper));
    }

    @ApiOperation(value = "查看微信公众号标签")
    @RequiresPermissions("wx:gzhTag:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        GzhTag gzhTag = gzhTagService.getById(id);
        return Result.ok().add(gzhTag);
    }

    @Log
    @ApiOperation(value = "创建微信公众号标签")
    @RequiresPermissions("wx:gzhTag:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhTag gzhTag) {
        log.info("创建微信公众号标签, 入参：{}", gzhTag);
        gzhTagService.save(gzhTag);
        return Result.ok().add(gzhTag);
    }

    @Log
    @ApiOperation(value = "更新微信公众号标签")
    @RequiresPermissions("wx:gzhTag:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody GzhTag gzhTag) {
        log.info("更新微信公众号标签, 入参：{}", gzhTag);
        gzhTagService.updateById(gzhTag);
        return Result.ok().add(gzhTag);
    }

    @Log
    @ApiOperation(value = "删除微信公众号标签")
    @RequiresPermissions("wx:gzhTag:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除微信公众号标签, ids={}", ids);
        gzhTagService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改微信公众号标签状态")
    @RequiresPermissions("wx:gzhTag:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改微信公众号标签状态, 入参：{}", changeStatusDTO);
        GzhTag gzhTag = new GzhTag();
        BeanUtils.copyProperties(changeStatusDTO, gzhTag);
        gzhTagService.updateById(gzhTag);
        return Result.ok().add(gzhTag);
    }

}
