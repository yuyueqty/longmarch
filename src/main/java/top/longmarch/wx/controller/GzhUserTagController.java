//package top.longmarch.wx.controller;
//
//
//import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//import top.longmarch.core.common.Constant;
//import top.longmarch.core.annotation.Log;
//import top.longmarch.core.common.PageFactory;
//import top.longmarch.core.common.Result;
//import top.longmarch.core.utils.LmUtils;
//import top.longmarch.sys.entity.vo.ChangeStatusDTO;
//import top.longmarch.wx.entity.GzhUserTag;
//import top.longmarch.wx.service.IGzhUserTagService;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p>
// * 粉丝标签表 前端控制器
// * </p>
// *
// * @author YuYue
// * @since 2020-04-19
//*/
//@Api(value = "粉丝标签表模块", tags = "粉丝标签表模块接口")
//@RestController
//@RequestMapping("/wx/gzh-user-tag")
//public class GzhUserTagController {
//
//    private static final Logger log = LoggerFactory.getLogger(GzhUserTagController.class);
//    @Autowired
//    private IGzhUserTagService gzhUserTagService;
//
//    @ApiOperation(value = "搜索粉丝标签表")
//    @PostMapping("/search")
//    public Result search(@RequestBody(required = false) Map<String, Object> params) {
//        IPage<GzhUserTag> page = PageFactory.getInstance(params);
//        LambdaQueryWrapper<GzhUserTag> wrapper = new LambdaQueryWrapper<>();
//
//
//
//        return Result.ok().add(gzhUserTagService.page(page, wrapper));
//    }
//
//    @ApiOperation(value = "查看粉丝标签表")
//    @RequiresPermissions("wx:gzhUserTag:show")
//    @GetMapping("/show/{id}")
//    public Result show(@PathVariable("id")Long id) {
//        GzhUserTag gzhUserTag = gzhUserTagService.getById(id);
//        return Result.ok().add(gzhUserTag);
//    }
//
//    @Log
//    @ApiOperation(value = "创建粉丝标签表")
//    @RequiresPermissions("wx:gzhUserTag:create")
//    @PostMapping("/create")
//    public Result create(@Validated @RequestBody GzhUserTag gzhUserTag) {
//        log.info("创建粉丝标签表, 入参：{}", gzhUserTag);
//        gzhUserTagService.save(gzhUserTag);
//        return Result.ok().add(gzhUserTag);
//    }
//
//    @Log
//    @ApiOperation(value = "更新粉丝标签表")
//    @RequiresPermissions("wx:gzhUserTag:update")
//    @PostMapping("/update")
//    public Result update(@Validated @RequestBody GzhUserTag gzhUserTag) {
//        log.info("更新粉丝标签表, 入参：{}", gzhUserTag);
//        gzhUserTagService.updateById(gzhUserTag);
//        return Result.ok().add(gzhUserTag);
//    }
//
//    @Log
//    @ApiOperation(value = "删除粉丝标签表")
//    @RequiresPermissions("wx:gzhUserTag:delete")
//    @PostMapping("/delete")
//    public Result delete(@RequestBody Long[] ids) {
//        log.info("删除粉丝标签表, ids={}", ids);
//        gzhUserTagService.removeByIds(Arrays.asList(ids));
//        return Result.ok();
//    }
//
//    @Log
//    @ApiOperation(value = "修改粉丝标签表状态")
//    @RequiresPermissions("wx:gzhUserTag:update")
//    @PostMapping("/changeStatus")
//    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
//        log.info("修改粉丝标签表状态, 入参：{}", changeStatusDTO);
//        GzhUserTag gzhUserTag = new GzhUserTag();
//        BeanUtils.copyProperties(changeStatusDTO, gzhUserTag);
//        gzhUserTagService.updateById(gzhUserTag);
//        return Result.ok().add(gzhUserTag);
//    }
//
//}
