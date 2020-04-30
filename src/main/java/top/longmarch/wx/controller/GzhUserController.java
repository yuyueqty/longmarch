package top.longmarch.wx.controller;


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
import top.longmarch.core.utils.LmUtils;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhUserService;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 粉丝表 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-04-18
 */
@Api(value = "粉丝表模块", tags = "粉丝表模块接口")
@RestController
@RequestMapping("/wx/gzh-user")
public class GzhUserController {

    private static final Logger log = LoggerFactory.getLogger(GzhUserController.class);
    @Autowired
    private IGzhUserService gzhUserService;
    @Autowired
    private IGzhAccountService gzhAccountService;

    @ApiOperation(value = "搜索粉丝表")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhUser> page = PageFactory.getInstance(params);
        QueryWrapper<GzhUser> wrapper = new QueryWrapper<>();
        Object prop = params.get(Constant.PROP);
        Object order = params.get(Constant.ORDER);
        if (LmUtils.isNotBlank(prop) && LmUtils.isNotBlank(order)) {
            boolean isAsc = "ascending".equals(order);
            wrapper.orderBy(true, isAsc, prop.toString());
        }

        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getDefaultAccount, 1)
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId()));
        if (gzhAccount == null) {
            wrapper.lambda().eq(GzhUser::getGzhId, -1);
        } else {
            wrapper.lambda().eq(GzhUser::getGzhId, gzhAccount.getId());
        }

        wrapper.lambda().eq(GzhUser::getCreateBy, UserUtil.getUserId());
        wrapper.lambda().eq(LmUtils.isNotBlank(params.get("sex")), GzhUser::getSex, params.get("sex"));
        wrapper.lambda().eq(LmUtils.isNotBlank(params.get("country")), GzhUser::getCountry, params.get("country"));
        wrapper.lambda().eq(LmUtils.isNotBlank(params.get("province")), GzhUser::getProvince, params.get("province"));
        wrapper.lambda().eq(LmUtils.isNotBlank(params.get("city")), GzhUser::getCity, params.get("city"));
        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        wrapper.lambda().like(LmUtils.isNotBlank(fuzzySearch), GzhUser::getNickname, fuzzySearch);

        return Result.ok().add(gzhUserService.page(page, wrapper));
    }

    @ApiOperation(value = "查看粉丝表")
    @RequiresPermissions("wx:gzhUser:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        GzhUser gzhUser = gzhUserService.getById(id);
        return Result.ok().add(gzhUser);
    }

    @Log
    @ApiOperation(value = "创建粉丝表")
    @RequiresPermissions("wx:gzhUser:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhUser gzhUser) {
        log.info("创建粉丝表, 入参：{}", gzhUser);
        gzhUserService.save(gzhUser);
        return Result.ok().add(gzhUser);
    }

    @Log
    @ApiOperation(value = "更新粉丝表")
    @RequiresPermissions("wx:gzhUser:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody GzhUser gzhUser) {
        log.info("更新粉丝表, 入参：{}", gzhUser);
        gzhUserService.updateById(gzhUser);
        return Result.ok().add(gzhUser);
    }

    @Log
    @ApiOperation(value = "删除粉丝表")
    @RequiresPermissions("wx:gzhUser:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除粉丝表, ids={}", ids);
        gzhUserService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改粉丝表状态")
    @RequiresPermissions("wx:gzhUser:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改粉丝表状态, 入参：{}", changeStatusDTO);
        GzhUser gzhUser = new GzhUser();
        BeanUtils.copyProperties(changeStatusDTO, gzhUser);
        gzhUserService.updateById(gzhUser);
        return Result.ok().add(gzhUser);
    }

}
