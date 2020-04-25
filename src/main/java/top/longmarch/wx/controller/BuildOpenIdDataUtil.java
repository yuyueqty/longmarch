package top.longmarch.wx.controller;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import top.longmarch.wx.entity.export.*;

import java.util.*;

public class BuildOpenIdDataUtil {

    private static final Map<String, List<String>> field_tag = new HashMap<>();
    private static final List<String> tags_1 = Arrays.asList("抗压", "执行力", "主动性", "创造性", "追求自我提升", "多线程处理", "偏好合作", "自信", "自我认同");
    private static final List<String> tags_2 = Arrays.asList("抗压", "执行力", "主动性", "创造性", "追求自我提升", "多线程处理", "偏好合作", "自信", "自我认同");
    private static final List<String> tags_3 = Arrays.asList("抗压", "执行力", "主动性", "创造性", "追求自我提升", "多线程处理", "偏好合作", "自信", "自我认同");
    private static final List<String> tags_4 = Arrays.asList("抗压", "执行力", "主动性", "创造性", "追求自我提升", "多线程处理", "偏好合作", "自信", "自我认同");

    static {
        field_tag.put("1", tags_1);
        field_tag.put("2", tags_2);
        field_tag.put("3", tags_3);
        field_tag.put("4", tags_4);
    }

    public static void main(String[] args) {
        String a = "抗压";
        System.out.println(a.length());
    }

    private static List<String> getFieldList(String field) {
        List<String> tags = field_tag.get(field);
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

    public static void buildMapDataV2(List<ExcelExportEntity> entity,
                                    List<Map<String, Object>> list,
                                    String field,
                                    List<UserExportV2> userTagExportList) {
        entity.add(new ExcelExportEntity("OpenID", "openId"));
        entity.add(new ExcelExportEntity("昵称", "nickname"));
        entity.add(new ExcelExportEntity("头像", "headImgUrl"));
        entity.add(new ExcelExportEntity("性别", "sexDesc"));
        entity.add(new ExcelExportEntity("国家", "country"));
        entity.add(new ExcelExportEntity("省份", "province"));
        entity.add(new ExcelExportEntity("城市", "city"));

        List<String> fieldList = getFieldList(field);
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
    public static void buildMapData(List<ExcelExportEntity> entity,
                              List<Map<String, Object>> list,
                             String field,
                              List<UserTagExport> userTagExportList) {
        entity.add(new ExcelExportEntity("OpenID", "openId"));
        entity.add(new ExcelExportEntity("昵称", "nickname"));
        entity.add(new ExcelExportEntity("头像", "headImgUrl"));
        entity.add(new ExcelExportEntity("性别", "sexDesc"));
        entity.add(new ExcelExportEntity("国家", "country"));
        entity.add(new ExcelExportEntity("省份", "province"));
        entity.add(new ExcelExportEntity("城市", "city"));

        List<String> fieldList = getFieldList(field);
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
        for (UserTagExport userTagExport : userTagExportList) {
            map = new HashMap();
            UserExport userExport = userTagExport.getUserExport();
            map.put("openId", userExport.getOpenId());
            map.put("nickname", userExport.getNickname());
            map.put("headImgUrl", userExport.getHeadImgUrl());
            map.put("sexDesc", userExport.getSexDesc());
            map.put("country", userExport.getCountry());
            map.put("province", userExport.getProvince());
            map.put("city", userExport.getCity());

            List<TagExport> tagExportList = userTagExport.getTagExportList();
            for (int i = 0; i < tagExportList.size(); i++) {
                TagExport tagExport = tagExportList.get(i);
                map.put("rank_" + i, tagExport.getRank());
                map.put("score_" + i, tagExport.getScore());
            }
            list.add(map);
        }
    }

}
