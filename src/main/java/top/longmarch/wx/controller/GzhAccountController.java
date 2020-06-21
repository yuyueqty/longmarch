package top.longmarch.wx.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.dto.ChangeStatusDTO;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.service.IGzhAccountService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统公众号表 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
*/
@Api(value = "系统公众号表模块", tags = "系统公众号表模块接口")
@RestController
@RequestMapping("/wx/gzh-account")
public class GzhAccountController {

    private static final Logger log = LoggerFactory.getLogger(GzhAccountController.class);
    @Autowired
    private IGzhAccountService gzhAccountService;

    @ApiOperation(value = "搜索系统公众号表")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhAccount> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<GzhAccount> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GzhAccount::getCreateBy, UserUtil.getUserId());
        return Result.ok().add(gzhAccountService.page(page, wrapper));
    }

    @ApiOperation(value = "查看系统公众号表")
    @RequiresPermissions("wx:gzhAccount:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        GzhAccount gzhAccount = gzhAccountService.getById(id);
        return Result.ok().add(gzhAccount);
    }

    @Log
    @ApiOperation(value = "创建系统公众号表")
    @RequiresPermissions("wx:gzhAccount:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhAccount gzhAccount) {
        log.info("创建系统公众号表, 入参：{}", gzhAccount);
        gzhAccountService.save(gzhAccount);
        return Result.ok().add(gzhAccount);
    }

    @Log
    @ApiOperation(value = "更新系统公众号表")
    @RequiresPermissions("wx:gzhAccount:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody GzhAccount gzhAccount) {
        log.info("更新系统公众号表, 入参：{}", gzhAccount);
        gzhAccountService.updateById(gzhAccount);
        return Result.ok().add(gzhAccount);
    }

    @Log
    @ApiOperation(value = "删除系统公众号表")
    @RequiresPermissions("wx:gzhAccount:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除系统公众号表, ids={}", ids);
        gzhAccountService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改系统公众号表状态")
    @RequiresPermissions("wx:gzhAccount:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改系统公众号表状态, 入参：{}", changeStatusDTO);
        GzhAccount gzhAccount = new GzhAccount();
        BeanUtils.copyProperties(changeStatusDTO, gzhAccount);
        gzhAccountService.updateById(gzhAccount);
        return Result.ok().add(gzhAccount);
    }

    @Log
    @ApiOperation(value = "设置默认公众号")
    @RequiresPermissions("wx:gzhAccount:setting")
    @GetMapping("/changeDefault/{gzh_id}")
    public Result changeDefault(@PathVariable Long gzh_id) {
        log.info("修改默认公众号, 入参：{}", gzh_id);
        Long userId = UserUtil.getUserId();
        gzhAccountService.update(new UpdateWrapper<GzhAccount>().set("is_default_account", 0).eq("create_by", userId));
        gzhAccountService.update(new UpdateWrapper<GzhAccount>().set("is_default_account", 1).eq("create_by", userId).eq("id", gzh_id));
        return Result.ok();
    }

}
