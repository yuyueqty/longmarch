package top.longmarch.core.shiro.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.core.shiro.service.UserIRolePermissionCacheService;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.service.impl.LMCacheManage;

@Slf4j
@Api(value = "ActiveUser", tags = "活跃用户操作接口")
@RestController
public class ActiveUserController {

    @Autowired
    private UserIRolePermissionCacheService userIRolePermissionCacheService;
    @Autowired
    private LMCacheManage lmCacheManage;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation(value = "Redis Test")
    @GetMapping("/redis")
    public Result redis(@RequestParam String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return Result.ok().add(value);
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public Result info() {
        return Result.ok().add(userIRolePermissionCacheService.getActivityUserInfo(UserUtil.getUserId()));
    }

    @ApiOperation(value = "刷新用户缓存")
    @GetMapping("/refresh")
    public Result refresh() {
        lmCacheManage.cleanActivityUserInfo(UserUtil.getUserId());
        return Result.ok();
    }

    @ApiOperation(value = "在线用户数")
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
        log.info("踢出用户：username={}", username);
        lmCacheManage.removeOnlineUser(username);
        return Result.ok();
    }

    @ApiOperation(value = "用户未登录")
    @GetMapping("/noLogin")
    public Result noLogin() {
        return Result.fail(10, "用户未登录");
    }

}
