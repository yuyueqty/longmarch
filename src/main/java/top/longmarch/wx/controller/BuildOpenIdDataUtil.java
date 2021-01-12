package top.longmarch.wx.controller;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import lombok.extern.slf4j.Slf4j;
import top.longmarch.wx.entity.export.TagExportV2;
import top.longmarch.wx.entity.export.UserExportV2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class BuildOpenIdDataUtil {


    public static void buildMapData(List<ExcelExportEntity> entity,
                                    List<Map<String, Object>> list,
                                    List<String> fieldList,
                                    List<UserExportV2> userTagExportList) {
        entity.add(new ExcelExportEntity("OpenID", "openId"));
        entity.add(new ExcelExportEntity("昵称", "nickname"));
        entity.add(new ExcelExportEntity("头像", "headImgUrl"));
        entity.add(new ExcelExportEntity("性别", "sexDesc"));
        entity.add(new ExcelExportEntity("国家", "country"));
        entity.add(new ExcelExportEntity("省份", "province"));
        entity.add(new ExcelExportEntity("城市", "city"));

        fieldList = getFieldList(fieldList);
        for (int i = 0; i < fieldList.size(); i++) {
            String f = fieldList.get(i);
            String[] split = f.split(":");
            if (split.length == 3) {
                entity.add(new ExcelExportEntity(split[0], split[1], Integer.valueOf(split[2])));
            } else {
                entity.add(new ExcelExportEntity(split[0], split[1]));
            }
        }

        Map<String, Object> map;
        for (UserExportV2 userExport : userTagExportList) {
            map = new HashMap();
            map.put("openId", userExport.getOpenId());
            map.put("nickname", userExport.getNickname());
            map.put("headImgUrl", userExport.getHeadImgUrl());
            map.put("sexDesc", userExport.getSexDesc());
            map.put("country", userExport.getCountry());
            map.put("province", userExport.getProvince());
            map.put("city", userExport.getCity());

            List<TagExportV2> tagExportList = userExport.getTags();
            for (int i = 0; i < tagExportList.size(); i++) {
                TagExportV2 tagExport = tagExportList.get(i);
                map.put("rank_" + i, tagExport.getRank());
                map.put("score_" + i, tagExport.getScore());
            }
            list.add(map);
        }
    }

    private static List<String> getFieldList(List<String> tags) {
        List<String> fieldList = new ArrayList<>();
        for (int i = 0; i < tags.size(); i++) {
            String tag = tags.get(i);
            int length = tag.length();
            if (length > 2) {
                fieldList.add(String.format("%s(等级):rank_%s:%s", tag, i, (length * 4)));
                fieldList.add(String.format("%s(评分):score_%s:%s", tag, i, (length * 4)));
            } else {
                fieldList.add(String.format("%s(等级):rank_%s", tag, i));
                fieldList.add(String.format("%s(评分):score_%s", tag, i));
            }
        }
        return fieldList;
    }

}
