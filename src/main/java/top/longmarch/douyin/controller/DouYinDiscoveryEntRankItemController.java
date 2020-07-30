package top.longmarch.douyin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinDiscoveryEntRankItemService;

@RestController
public class DouYinDiscoveryEntRankItemController {

    @Autowired
    private DouYinDiscoveryEntRankItemService douYinDiscoveryEntRankItemService;

    @GetMapping("/discoveryEntRankItemGet")
    public Result discoveryEntRankItemGet(@RequestParam Integer type, @RequestParam(required = false) Integer version) {
        return Result.ok().add(douYinDiscoveryEntRankItemService.discoveryEntRankItemGet(type, version));
    }

    @GetMapping("/discoveryEntRankVersionGet")
    public Result discoveryEntRankVersionGet(@RequestParam Integer type,
                                             @RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                                             @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Integer cursor) {
        return Result.ok().add(douYinDiscoveryEntRankItemService.discoveryEntRankVersionGet(type, count, cursor));
    }

}
