package top.longmarch.core.shiro.api;

import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.Result;
import top.longmarch.core.shiro.model.RegisterUser;
import top.longmarch.sys.entity.Parameter;
import top.longmarch.sys.entity.SysParams;
import top.longmarch.sys.entity.User;
import top.longmarch.sys.service.IParameterService;
import top.longmarch.sys.service.IUserService;

import java.util.Date;

@Api(value = "用户注册模块", tags = "用户注册模块接口")
@RestController
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IParameterService parameterService;

    @Log
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@Validated @RequestBody RegisterUser registerUser) {
        log.info("用户注册信息, 入参：{}", registerUser);
        userService.saveUser(RegisterUser2UserInfo(registerUser));
        return Result.ok().add(registerUser);
    }

    private User RegisterUser2UserInfo(RegisterUser registerUser) {
        User user = new User();
        SysParams sysParams = JSONUtil.toBean(parameterService.getParameterByName(Constant.SYS_PARAMS).getParamValue(), SysParams.class);
        user.setHeadImgUrl(sysParams.getHeadImgUrl());
        String paramValue = parameterService.getParameterByName(Constant.DEFAULT_USER_ROLE).getParamValue();
        user.setRoleIds(JSONUtil.parseObj(paramValue).getStr("roleId"));
        user.setCreateBy(0L);
        user.setCreateTime(new Date());
        BeanUtils.copyProperties(registerUser, user);
        return user;
    }

}
