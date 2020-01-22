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
import top.longmarch.cms.entity.Channel;
import top.longmarch.cms.service.IChannelService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 频道 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "频道模块", tags = "频道模块接口")
@RestController
@RequestMapping("/cms/channel")
public class ChannelController {

    private static final Logger log = LoggerFactory.getLogger(ChannelController.class);
    @Autowired
    private IChannelService hannelService;

    @ApiOperation(value="搜索频道")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<Channel> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<Channel> wrapper = new LambdaQueryWrapper<>();
        // 自定义查询条件
        return Result.ok().add(hannelService.page(page, wrapper));
    }

    @ApiOperation(value="查看频道")
    @RequiresPermissions("cms:channel:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        Channel channel = hannelService.getById(id);
        return Result.ok().add(channel);
    }

    @Log
    @ApiOperation(value="创建频道")
    @RequiresPermissions("cms:channel:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Channel channel) {
        log.info("创建频道, 入参：{}", channel);
        hannelService.save(channel);
        return Result.ok().add(channel);
    }

    @Log
    @ApiOperation(value="更新频道")
    @RequiresPermissions("cms:channel:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Channel channel) {
        log.info("更新频道, 入参：{}", channel);
        hannelService.updateById(channel);
        return Result.ok().add(channel);
    }

    @Log
    @ApiOperation(value="删除频道")
    @RequiresPermissions("cms:channel:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除频道, ids={}", ids);
        hannelService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

}
