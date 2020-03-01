package top.longmarch.sys.controller;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.sys.service.impl.LMCacheManage;

@Api(value = "缓存管理", tags = "缓存管理接口")
@RestController
@RequestMapping("/sys/cache")
public class CacheManageController {

    @Autowired
    private LMCacheManage lmCacheManage;

    public static void main(String[] args) {
        String lastUpdateTime = DateUtil.formatBetween(1582991488);
        String creationTime = DateUtil.formatBetween(1582991488);
        String lastAccessTime = DateUtil.formatBetween(1582991488);
        System.out.println(lastUpdateTime);
        System.out.println(creationTime);
        System.out.println(lastAccessTime);
    }

    @ApiOperation(value = "缓存列表")
    @PostMapping("/list")
    public Result list() {
        return Result.ok().add(lmCacheManage.getAllCache());
    }

}
