package com;

import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.swagger.models.auth.In;
import org.apache.http.client.methods.HttpGet;
import org.w3c.dom.Document;

import java.util.*;
import java.util.stream.Collectors;

public class VideoTest {

    public static void main(String[] args) {
        String url = "http://www.zdziyuan.com/inc/ldg_sea.php";
        JSONArray video_list = JSONUtil.parseFromXml(HttpUtil.get(url)).getJSONObject("rss").getJSONObject("list").getJSONArray("video");
        System.out.println(video_list);
        List<String> ids = video_list.stream().map(json -> ((JSONObject) json).getStr("id")).collect(Collectors.toList());

        String url2 = url + "?ac=videolist&ids="+String.join(",", ids);
        JSONObject jsonObject2 = JSONUtil.parseFromXml(HttpUtil.get(url2));
        System.out.println(jsonObject2.getJSONObject("rss").getJSONObject("list").getJSONArray("video"));
    }


}
