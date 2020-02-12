package top.longmarch.core.shiro.api;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Result;
import top.longmarch.core.shiro.model.LoginUser;
import top.longmarch.core.shiro.service.UserIRolePermissionService;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.User;
import top.longmarch.sys.entity.vo.RoutesTree;
import top.longmarch.sys.service.IDictionaryService;

import java.util.*;

@Api(value = "用户登陆模块", tags = "用户登陆模块接口")
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private UserIRolePermissionService userIRolePermissionService;


    @Log(type = Log.LogType.LOGIN)
    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginUser loginUser) {
        UUID uuid = UUID.randomUUID();
        Map<String, Object> result = new HashMap<>();
        result.put("token", uuid);
        String username = loginUser.getUsername();
        String password = loginUser.getPassword();
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return Result.ok().add(result);
        }
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        try {
            subject.login(token);
        } catch (UnknownAccountException uae) {
            return Result.fail(11, "账户不存在");
        } catch (IncorrectCredentialsException ice) {
            return Result.fail(12, "密码不正确");
        } catch (LockedAccountException lae) {
            return Result.fail(13, "账户已锁定");
        } catch (ExcessiveAttemptsException eae) {
            return Result.fail(14, "用户名或密码错误次数过多");
        } catch (AuthenticationException ae) {
            return Result.fail(15, "用户名或密码不正确");
        }
        return Result.ok().add(result);
    }

    @ApiOperation(value = "用户退出")
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.ok();
    }

    @ApiOperation(value = "用户信息")
    @GetMapping("/info")
    public Result info() {
        User user = userIRolePermissionService.getUserByUserId(UserUtil.loginUser().getId());
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("introduction", "测试系统");
        userMap.put("username", user.getUsername());
        userMap.put("avatar", user.getHeadImgUrl());
        userMap.put("dictionary", dictionaryService.getAllDict());
        Set<String> roles = userIRolePermissionService.getUserRoleByUserId(user.getId());
        if (roles == null || roles.size() == 0) {
            roles = new LinkedHashSet<>();
            roles.add("未授权组");
        }
        userMap.put("roles", roles);
        userMap.put("permissions", userIRolePermissionService.getUserPermissionByUserId(user.getId()));
        userMap.put("menus", userIRolePermissionService.getUserMenusByUserId(user.getId()));
        // 用户信息
        Map<String, Object> showUser = new HashMap<>();
        showUser.put("userId", user.getId());
        showUser.put("username", user.getUsername());
        showUser.put("status", user.getStatus());
        showUser.put("createTime", user.getCreateTime());
        showUser.put("updateTime", user.getUpdateTime());
        userMap.put("userInfo", showUser);

        userMap.put("routes", JSONUtil.parse(buildRoutes(user.getId())));
        return Result.ok().add(userMap);
    }

    public List<RoutesTree> buildRoutes(Long userId) {
        return userIRolePermissionService.getRoutes(userId);
    }

    public List<Map<String, Object>> buildRoutes2(Long userId) {
        List<Map<String, Object>> routes = new ArrayList<>();
        List<String> roles = null;

        Map<String, Object> systemRoute = new HashMap<>();
        systemRoute.put("path", "/system");
        systemRoute.put("component", "Layout");
        systemRoute.put("redirect", "/system/user");
        systemRoute.put("name", "System");
        systemRoute.put("hidden", false);
        Map<String, Object> systemMeta = new HashMap<>();
        systemMeta.put("title", "system");
        systemMeta.put("icon", "tree");
        systemMeta.put("noCache", true);

//        roles = new ArrayList<>();
//        roles.add("sys:manage");
//        systemMeta.put("roles", roles);
        systemRoute.put("meta", systemMeta);


        List<Map<String, Object>> systemChildren = new ArrayList<>();

        Map<String, Object> userRoute = new HashMap<>();
        userRoute.put("path", "user");
        userRoute.put("component", "user/index");
        userRoute.put("name", "UserManage");
        userRoute.put("hidden", false);
        Map<String, Object> userMeta = new HashMap<>();
        userMeta.put("title", "userManage");
        userMeta.put("noCache", true);
        userMeta.put("icon", "user");
//        roles = new ArrayList<>();
//        roles.add("sys:user:manage");
//        userMeta.put("roles", roles);
        userRoute.put("meta", userMeta);
        systemChildren.add(userRoute);

        Map<String, Object> roleRoute = new HashMap<>();
        roleRoute.put("path", "role");
        roleRoute.put("component", "role/index");
        roleRoute.put("name", "RoleManage");
        roleRoute.put("hidden", false);
        Map<String, Object> roleMeta = new HashMap<>();
        roleMeta.put("title", "roleManage");
        roleMeta.put("noCache", true);
        roleMeta.put("icon", "peoples");
//        roles = new ArrayList<>();
//        roles.add("sys:role:manage");
//        roleMeta.put("roles", roles);
        roleRoute.put("meta", roleMeta);
        systemChildren.add(roleRoute);


        systemRoute.put("children", systemChildren);
        routes.add(systemRoute);
        return routes;
    }

}
