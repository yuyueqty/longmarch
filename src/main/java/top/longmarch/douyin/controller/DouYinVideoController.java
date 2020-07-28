package top.longmarch.douyin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.MD5;
import com.douyin.open.ApiException;
import com.douyin.open.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.request.DouyinParam;
import top.longmarch.douyin.service.DouYinVideoService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 发布抖音视频
 */
@Slf4j
@RestController
public class DouYinVideoController {

    @Autowired
    private DouYinVideoService douYinVideoService;

    @PostMapping("/videoUpload")
    public Result videoUpload(MultipartFile file) {
        VideoUploadResponseData data = new VideoUploadResponseData();
        try {
            String file_name = MD5.create().digestHex(file.getOriginalFilename());
            String file_suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
            File video = FileUtil.writeBytes(file.getBytes(), new File(file_name + "." + file_suffix));
            data = douYinVideoService.videoUpload(video).getData();
        } catch (Exception e) {
            log.error("上传视频文件失败：{}", e);
        }
        return Result.ok().add(data);
    }

    @PostMapping("/videoCreate")
    public Result videoCreate(VideoCreateBody body) {
        VideoCreateResponseData data = new VideoCreateResponseData();
        try {
            data = douYinVideoService.videoCreate(body).getData();
        } catch (ApiException e) {
            log.error("发布抖音视频失败：{}", e);
        }
        return Result.ok().add(data);
    }

    @GetMapping("/videoList")
    public Result videoList(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                            @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Long cursor) {
        List<Video> list = new ArrayList<>();
        try {
            list = douYinVideoService.videoList(count, cursor).getData().getList();
        } catch (ApiException e) {
            log.error("获取抖音视频列表失败：{}", e.getMessage());
        }
        return Result.ok().add(list);
    }

    @PostMapping("/videoDelete")
    public Result videoDelete(VideoDeleteBody body) {
        VideoDeleteResponseData data = new VideoDeleteResponseData();
        try {
            data = douYinVideoService.videoDelete(body).getData();
        } catch (ApiException e) {
            log.error("删除抖音视频失败：itemId={}, error={}", body.getItemId(), e);
        }
        return Result.ok().add(data);
    }

}
