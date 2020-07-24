package top.longmarch.douyin.controller;

import com.douyin.open.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.douyin.service.DouYinVideoReportDataService;

/**
 * 获取用户视频数据
 */
@RestController
public class DouYinVideoReportDataController {

    @Autowired
    private DouYinVideoReportDataService douYinVideoReportDataService;

    @GetMapping("/dataExternalItemBaseGet")
    public Object dataExternalItemBaseGet(@RequestParam String itemId) {
        try {
            return douYinVideoReportDataService.dataExternalItemBaseGet(itemId);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalItemCommentGet")
    public Object dataExternalItemCommentGet(@RequestParam String itemId, @RequestParam Integer dateType) {
        try {
            return douYinVideoReportDataService.dataExternalItemCommentGet(itemId, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalItemLikeGet")
    public Object dataExternalItemLikeGet(@RequestParam String itemId, @RequestParam Integer dateType) {
        try {
            return douYinVideoReportDataService.dataExternalItemLikeGet(itemId, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalItemPlayGet")
    public Object dataExternalItemPlayGet(@RequestParam String itemId, @RequestParam Integer dateType) {
        try {
            return douYinVideoReportDataService.dataExternalItemPlayGet(itemId, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/dataExternalItemShareGet")
    public Object dataExternalItemShareGet(@RequestParam String itemId, @RequestParam Integer dateType) {
        try {
            return douYinVideoReportDataService.dataExternalItemShareGet(itemId, dateType);
        } catch (ApiException e) {
            e.printStackTrace();
            return null;
        }
    }

}
