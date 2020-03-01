package top.longmarch.core.shiro.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.shiro.service.UserIRolePermissionCacheService;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.service.impl.LMCacheManage;

@Api(value = "用户信息模块", tags = "用户用户信息接口")
@RestController
public class UserInfoController {

    @Autowired
    private UserIRolePermissionCacheService userIRolePermissionCacheService;
    @Autowired
    private LMCacheManage lmCacheManage;

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public Result info() {
        return Result.ok().add(userIRolePermissionCacheService.getActivityUserInfo(UserUtil.getUserId()));
    }

    @ApiOperation(value = "刷新当前用户信息")
    @GetMapping("/refresh")
    public Result refresh() {
        lmCacheManage.cleanActivityUserInfo(UserUtil.getUserId());
        return Result.ok();
    }

    @ApiOperation(value = "在线总用户")
    @GetMapping("/onlineUsersCount")
    public Result onlineUsersCount() {
        return Result.ok().add(lmCacheManage.getOnlineUsersCount());
    }

    @ApiOperation(value = "在线用户")
    @GetMapping("/onlineUsers")
    public Result onlineUsers() {
        return Result.ok().add(lmCacheManage.getOnlineUsers());
    }

    @ApiOperation(value = "踢出用户")
    @GetMapping("/kickOutUser")
    public Result kickOutUser(@RequestParam String username) {
        lmCacheManage.removeOnlineUser(username);
        return Result.ok();
    }

}
