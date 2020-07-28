package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import com.douyin.open.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinVideoReportDataService;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取用户视频数据
 */
@RestController
public class DouYinVideoReportDataController {

    @Autowired
    private DouYinVideoReportDataService douYinVideoReportDataService;

    @GetMapping("/dataExternalItemBaseGet")
    public Result dataExternalItemBaseGet(@RequestParam String itemId) {
        ExternalItemBase result = new ExternalItemBase();
        try {
            result = douYinVideoReportDataService.dataExternalItemBaseGet(itemId).getData().getResult();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(result);
    }

    @GetMapping("/dataExternalItemCommentGet")
    public Result dataExternalItemCommentGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalItemComment> resultList = new ArrayList<>();
        try {
            resultList = douYinVideoReportDataService.dataExternalItemCommentGet(itemId, dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(resultList);
    }

    @GetMapping("/dataExternalItemLikeGet")
    public Result dataExternalItemLikeGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalItemLike> resultList = new ArrayList<>();
        try {
            resultList = douYinVideoReportDataService.dataExternalItemLikeGet(itemId, dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(resultList);
    }

    @GetMapping("/dataExternalItemPlayGet")
    public Result dataExternalItemPlayGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalItemPlay> resultList = new ArrayList<>();
        try {
            resultList = douYinVideoReportDataService.dataExternalItemPlayGet(itemId, dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(resultList);
    }

    @GetMapping("/dataExternalItemShareGet")
    public Result dataExternalItemShareGet(
            @RequestParam String itemId, @RequestParam(required = false, defaultValue = DouyinParam.DATE_TYPE) Integer dateType) {
        List<ExternalItemShare> resultList = new ArrayList<>();
        try {
            resultList = douYinVideoReportDataService.dataExternalItemShareGet(itemId, dateType).getData().getResultList();
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return Result.ok().add(resultList);
    }

}
