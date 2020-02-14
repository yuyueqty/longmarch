package top.longmarch.core.shiro.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.shiro.service.UserIRolePermissionCacheService;
import top.longmarch.core.utils.UserUtil;

@Api(value = "用户信息模块", tags = "用户用户信息接口")
@RestController
public class UserInfoController {

    @Autowired
    private UserIRolePermissionCacheService userIRolePermissionCacheService;

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public Result info() {
        return Result.ok().add(userIRolePermissionCacheService.getActivityUserInfo(UserUtil.getUserId()));
    }

    @ApiOperation(value = "刷新用户信息")
    @GetMapping("/refresh")
    public Result refresh() {
        userIRolePermissionCacheService.cleanActivityUserInfo(UserUtil.getUserId());
        return Result.ok();
    }

}
