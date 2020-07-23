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
import top.longmarch.douyin.service.DouYinVideoService;

import java.io.File;

/**
 * 发布抖音视频
 */
@Slf4j
@RestController
public class DouYinVideoController {

    @Autowired
    private DouYinVideoService douYinVideoService;

    @PostMapping("/videoUpload")
    public Object videoUpload(@RequestParam String token, MultipartFile file) {
        VideoUploadResponse response = new VideoUploadResponse();
        try {
            String file_name = MD5.create().digestHex(file.getOriginalFilename());
            String file_suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
            File video = FileUtil.writeBytes(file.getBytes(), new File(file_name + "." + file_suffix));
            response = douYinVideoService.videoUpload(token, video);
        } catch (Exception e) {
            log.error("上传视频文件失败：{}", e);
        }
        return response;
    }

    @PostMapping("/videoCreate")
    public Object videoCreate(@RequestParam String token, VideoCreateBody body) {
        VideoCreateResponse response = new VideoCreateResponse();
        try {
            response = douYinVideoService.videoCreate(token, body);
        } catch (ApiException e) {
            log.error("发布抖音视频失败：{}", e);
        }
        return response;
    }

    @GetMapping("/videoList")
    public Object videoList(@RequestParam String token, @RequestParam Integer count, @RequestParam Long cursor) {
        VideoListResponse response = new VideoListResponse();
        try {
            response = douYinVideoService.videoList(token, count, cursor);
        } catch (ApiException e) {
            log.error("获取抖音视频列表失败：{}", e);
        }
        return response;
    }

    @PostMapping("/videoDelete")
    public Object videoDelete(@RequestParam String token, VideoDeleteBody body) {
        VideoDeleteResponse response = new VideoDeleteResponse();
        try {
            response = douYinVideoService.videoDelete(token, body);
        } catch (ApiException e) {
            log.error("删除抖音视频失败：itemId={}, error={}", body.getItemId(), e);
        }
        return response;
    }

}
