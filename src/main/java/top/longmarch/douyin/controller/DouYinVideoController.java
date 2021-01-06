package top.longmarch.douyin.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.MD5;
import com.douyin.open.models.VideoCreateAwemeCreateBody1;
import com.douyin.open.models.VideoCreateAwemeCreateInlineResponse200Data;
import com.douyin.open.models.VideoDeleteAwemeDeleteBody;
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
import java.io.IOException;

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
        String file_name = MD5.create().digestHex(file.getOriginalFilename());
        String file_suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
        File video = null;
        try {
            video = FileUtil.writeBytes(file.getBytes(), new File(file_name + "." + file_suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }
        VideoCreateAwemeCreateInlineResponse200Data data = douYinVideoService.videoUpload(video);
        System.out.println(data.toString());
        return Result.ok().add(data);
    }

    @PostMapping("/videoCreate")
    public Result videoCreate(VideoCreateAwemeCreateBody1 body) {
        return Result.ok().add(douYinVideoService.videoCreate(body));
    }

    @GetMapping("/videoList")
    public Result videoList(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                            @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Long cursor) {
        return Result.ok().add(douYinVideoService.videoList(count, cursor));
    }

    @GetMapping("/videoSearch")
    public Result videoSearch(@RequestParam(required = false, defaultValue = DouyinParam.COUNT) Integer count,
                              @RequestParam(required = false, defaultValue = DouyinParam.CURSOR) Long cursor,
                              @RequestParam String keyword) {
        return Result.ok().add(douYinVideoService.videoSearch(count, cursor, keyword));
    }

    @PostMapping("/videoDelete")
    public Result videoDelete(VideoDeleteAwemeDeleteBody body) {
        return Result.ok().add(douYinVideoService.videoDelete(body));
    }

}
