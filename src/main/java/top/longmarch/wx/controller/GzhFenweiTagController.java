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
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhFenweiTag;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhFenweiTagService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 公众号粉丝分维解析标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
*/
@Api(value = "公众号粉丝分维解析标签模块", tags = "公众号粉丝分维解析标签模块接口")
@RestController
@RequestMapping("/wx/gzh-fenwei-tag")
public class GzhFenweiTagController {

    private static final Logger log = LoggerFactory.getLogger(GzhFenweiTagController.class);
    @Autowired
    private IGzhFenweiTagService gzhFenweiTagService;
    @Autowired
    private IGzhAccountService gzhAccountService;

    @ApiOperation(value = "搜索公众号粉丝分维解析标签")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhFenweiTag> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<GzhFenweiTag> wrapper = new LambdaQueryWrapper<>();

        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getDefaultAccount, 1)
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId()));
        if (gzhAccount == null) {
            wrapper.eq(GzhFenweiTag::getGzhId, -1).eq(GzhFenweiTag::getFieldId, -1);
        } else {
            wrapper.eq(GzhFenweiTag::getGzhId, gzhAccount.getId()).eq(GzhFenweiTag::getFieldId, gzhAccount.getFwField());
        }

        wrapper.eq(LmUtils.isNotBlank(params.get("openId")), GzhFenweiTag::getOpenId, params.get("openId"));

        return Result.ok().add(gzhFenweiTagService.page(page, wrapper));
    }

    @ApiOperation(value = "查看公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        GzhFenweiTag gzhFenweiTag = gzhFenweiTagService.getById(id);
        return Result.ok().add(gzhFenweiTag);
    }

    @Log
    @ApiOperation(value = "创建公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhFenweiTag gzhFenweiTag) {
        log.info("创建公众号粉丝分维解析标签, 入参：{}", gzhFenweiTag);
        gzhFenweiTagService.save(gzhFenweiTag);
        return Result.ok().add(gzhFenweiTag);
    }

    @Log
    @ApiOperation(value = "更新公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody GzhFenweiTag gzhFenweiTag) {
        log.info("更新公众号粉丝分维解析标签, 入参：{}", gzhFenweiTag);
        gzhFenweiTagService.updateById(gzhFenweiTag);
        return Result.ok().add(gzhFenweiTag);
    }

    @Log
    @ApiOperation(value = "删除公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除公众号粉丝分维解析标签, ids={}", ids);
        gzhFenweiTagService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改公众号粉丝分维解析标签状态")
    @RequiresPermissions("wx:gzhFenweiTag:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改公众号粉丝分维解析标签状态, 入参：{}", changeStatusDTO);
        GzhFenweiTag gzhFenweiTag = new GzhFenweiTag();
        BeanUtils.copyProperties(changeStatusDTO, gzhFenweiTag);
        gzhFenweiTagService.updateById(gzhFenweiTag);
        return Result.ok().add(gzhFenweiTag);
    }

}
