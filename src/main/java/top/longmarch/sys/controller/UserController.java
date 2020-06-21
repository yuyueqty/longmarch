package top.longmarch.sys.controller;


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
import top.longmarch.core.common.Result;
import top.longmarch.sys.entity.User;
import top.longmarch.sys.entity.dto.ChangeStatusDTO;
import top.longmarch.sys.entity.dto.ChangePasswordDTO;
import top.longmarch.sys.entity.dto.CreateUpdateUserDTO;
import top.longmarch.sys.service.IUserService;

import java.util.Arrays;
import java.util.Map;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
*/
@Api(value = "用户模块", tags = "用户增删改查接口")
@RestController
@RequestMapping("/sys/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    @ApiOperation(value="搜索用户信息")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<User> userPage = userService.search(params);
        return Result.ok().add(userPage);
    }

    @ApiOperation(value="查看用户信息")
    @RequiresPermissions("sys:user:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id")Long id) {
        User user = userService.getById(id);
        return Result.ok().add(user);
    }

    @Log
    @ApiOperation(value="修改用户密码")
    @RequiresPermissions("sys:user:change:password")
    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) {
        log.info("修改用户密码, 入参：{}", changePasswordDTO);
        userService.changePassword(changePasswordDTO);
        return Result.ok();
    }

    @Log
    @ApiOperation(value="修改用户状态")
    @RequiresPermissions("sys:user:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改用户状态, 入参：{}", changeStatusDTO);
        userService.changeStatus(changeStatusDTO);
        return Result.ok();
    }

    @Log(noSaveFields = {"password"})
    @ApiOperation(value="创建用户信息")
    @RequiresPermissions("sys:user:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody CreateUpdateUserDTO createUpdateUserDTO) {
        log.info("创建用户信息, 入参：{}", createUpdateUserDTO);
        userService.createUpdateUser(createUpdateUserDTO);
        return Result.ok().add(createUpdateUserDTO);
    }

    @Log(noSaveFields = {"password"})
    @ApiOperation(value="更新用户信息")
    @RequiresPermissions("sys:user:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody CreateUpdateUserDTO createUpdateUserDTO) {
        log.info("更新用户信息, 入参：{}", createUpdateUserDTO);
        userService.createUpdateUser(createUpdateUserDTO);
        return Result.ok().add(createUpdateUserDTO);
    }

    @Log
    @ApiOperation(value="删除用户信息")
    @RequiresPermissions("sys:user:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除用户信息, ids={}", ids);
        userService.removeUser(Arrays.asList(ids));
        return Result.ok();
    }

}
