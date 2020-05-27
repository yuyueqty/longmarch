package top.longmarch.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.wx.entity.GzhUser;
import top.longmarch.wx.service.IGzhUserService;

@Api(value = "开放接口", tags = "微信开放接口")
@RestController
@RequestMapping("/api")
public class WxOpenApi {

    @Autowired
    private IGzhUserService gzhUserService;

    @ApiOperation(value = "获取微信用户列表")
    @GetMapping("/getWxUserInfoList")
    public Result list(@RequestParam Long userId, @RequestParam Long gzhId,
                       @RequestParam Integer pageNum, @RequestParam Integer pageSize) {
        IPage<GzhUser> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<GzhUser> wrapper = new LambdaQueryWrapper();
        wrapper.eq(GzhUser::getCreateBy, userId).eq(GzhUser::getGzhId, gzhId);
        IPage pageData = gzhUserService.page(page, wrapper);
        return Result.ok().add(pageData);
    }

}
