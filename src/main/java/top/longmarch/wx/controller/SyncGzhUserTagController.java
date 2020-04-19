package top.longmarch.wx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.wx.entity.GzhAccount;
import top.longmarch.wx.service.IGzhAccountService;
import top.longmarch.wx.service.IGzhUserService;

@Api(value = "同步公众号用户信息", tags = "同步Api接口")
@RestController
@RequestMapping("/wx/gzh-user")
public class SyncGzhUserTagController {

    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserService gzhUserService;

    @ApiOperation(value = "同步微信用户标签")
    @GetMapping("/syncWxUserTag")
    public Result syncWxUserTag(@RequestParam(required = false, defaultValue = "false") Boolean batchSync) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>().eq(GzhAccount::getDefaultAccount, 1));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }

        return Result.ok();
    }


}
