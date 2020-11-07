package top.longmarch.core.shiro.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Result;
import top.longmarch.core.shiro.model.LoginInfo;
import top.longmarch.core.shiro.model.RegisterInfo;
import top.longmarch.core.shiro.service.LoginAndRegisterService;
import top.longmarch.core.utils.UserUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Slf4j
@Api(value = "LoginAndRegister", tags = "用户登录注册接口")
@RestController
public class LoginAndRegisterController {

    @Autowired
    private LoginAndRegisterService loginAndRegisterService;

    @Log(type = Log.LogType.LOGIN)
    @ApiOperation(value = "用户登陆")
    @PostMapping(value = "/login")
    public Result login(HttpServletRequest request, @RequestBody @Validated LoginInfo loginInfo) {
        log.info("用户登录信息：username={}, password=***", loginInfo.getUsername());
        loginAndRegisterService.login(loginInfo);
        return Result.ok("登录成功").add("token", UUID.randomUUID());
    }

    @Log
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterInfo registerInfo) {
        log.info("用户注册信息：username={}, password=***, phone={}", registerInfo.getUsername(), registerInfo.getPhone());
        loginAndRegisterService.register(registerInfo);
        return Result.ok().add(registerInfo);
    }

    @ApiOperation(value = "用户退出")
    @GetMapping("/logout")
    public Result logout() {
        log.info("用户退出信息：username={}", UserUtil.getUsername());
        loginAndRegisterService.logout();
        return Result.ok();
    }

}
