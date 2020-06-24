package top.longmarch.api;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.common.Result;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Api(value = "开放接口", tags = "视频接口")
@RestController
@RequestMapping("/api")
public class VideoApi {

    @ApiOperation(value = "获取电影列表")
    @GetMapping("/video")
    public Result list(@RequestParam String u,
                       @RequestParam(required = false, defaultValue = "") String t,
                       @RequestParam(required = false, defaultValue = "") String h,
                       @RequestParam(required = false, defaultValue = "") String pg,
                       @RequestParam(required = false, defaultValue = "") String wd) {
        String url = String.format("%s?ac=list&rid=2&t=%s&h=%s&pg=%s&wd=%s", u, t, h, pg, wd);
        log.info("url = {}", url);
        JSONObject video_list = JSONUtil.parseFromXml(HttpUtil.get(url));
        return Result.ok().add(video_list);
    }

    @ApiOperation(value = "电影详情")
    @GetMapping("/video/{id}")
    public Result list(@PathVariable String id, @RequestParam String u) {
        String url = String.format("%s?ac=videolist&ids=%s", u, id);
        log.info("url = {}", url);
        JSONObject video_list = JSONUtil.parseFromXml(HttpUtil.get(url));
        return Result.ok().add(video_list);
    }

    @ApiOperation(value = "获取电影列表")
    @GetMapping("/video_list")
    public Result video_list(@RequestParam String u,
                       @RequestParam(required = false, defaultValue = "") String t,
                       @RequestParam(required = false, defaultValue = "") String h,
                       @RequestParam(required = false, defaultValue = "") String pg,
                       @RequestParam(required = false, defaultValue = "") String wd) {
        String url = String.format("%s?ac=list&rid=2&t=%s&h=%s&pg=%s&wd=%s", u, t, h, pg, wd);
        JSONArray list = JSONUtil.parseFromXml(HttpUtil.get(url)).getJSONObject("rss").getJSONObject("list").getJSONArray("video");
        log.info("url = {}", url);
        List<String> ids = list.stream().map(json -> ((JSONObject) json).getStr("id")).collect(Collectors.toList());
        String url2 = String.format("%s?ac=videolist&ids=%s", u, String.join(",", ids));
        log.info("url2 = {}", url2);
        JSONObject video_list = JSONUtil.parseFromXml(HttpUtil.get(url2));
        return Result.ok().add(video_list);
    }

    public static void main(String[] args) {
        String url = "http://www.zdziyuan.com/inc/ldg_sea.php?ac=list&rid=2&t=&h=&pg=&wd=";
        String url2 = "http://www.zdziyuan.com/inc/ldg_sea.php";
        String url3 = "http://api.iokzy.com/inc/ldg_sea.php";
//        JSONArray video_list = JSONUtil.parseFromXml(HttpUtil.get(url)).getJSONObject("rss").getJSONObject("list").getJSONArray("video");
//        System.out.println(video_list);
//        List<String> ids = video_list.stream().map(json -> ((JSONObject) json).getStr("id")).collect(Collectors.toList());
//
//        String url2 = url + "?ac=videolist&ids="+String.join(",", ids);
//        JSONObject jsonObject2 = JSONUtil.parseFromXml(HttpUtil.get(url2));
//        System.out.println(jsonObject2.getJSONObject("rss").getJSONObject("list").getJSONArray("video"));
//
//
        System.out.println(JSONUtil.parseFromXml(HttpUtil.get(url3)));
    }

}
