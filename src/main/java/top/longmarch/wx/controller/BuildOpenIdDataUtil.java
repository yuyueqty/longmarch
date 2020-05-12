package top.longmarch.wx.controller;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import top.longmarch.wx.entity.export.*;

import java.util.*;

public class BuildOpenIdDataUtil {

    private static final Map<String, List<String>> field_tag = new HashMap<>();
    private static final List<String> tags_1 = Arrays.asList("抗压", "执行力", "主动性", "创造性", "追求自我提升", "多线程处理", "偏好合作", "自信", "自我认同");
    private static final List<String> tags_2 = Arrays.asList("风险偏好指数", "银行产品信任指数", "认同权威指数", "付费意愿指数", "社交活跃指数", "思维激进指数", "亲密关系指数", "激励响应指数");
    private static final List<String> tags_3 = Arrays.asList("愿意付出成本去降低风险", "喜欢提前规划未来", "关注自己的身体健康", "做决定较慢", "购买时关注价格", "倾向于听从权威的建议", "倾向于参考别人的选择", "决策谨慎，喜欢进行客观全面的评估");
    private static final List<String> tags_4 = Arrays.asList("日常情绪的积极程度高", "善于建立与维护人际关系", "善于推陈出新", "喜欢对事情作出评价", "做事情倾向关注整体");
    private static final List<String> tags_5 = Arrays.asList("风险规避", "计划未来", "担心健康", "容易焦虑", "信任陌生人", "敌意", "互惠", "友善", "爱交际", "决策慢", "选择困难", "相信权威", "从众", "不喜欢做决定", "理性", "怕麻烦", "品牌忠诚", "价格敏感");

    static {
        field_tag.put("1", tags_1);
        field_tag.put("2", tags_2);
        field_tag.put("3", tags_3);
        field_tag.put("4", tags_4);
        field_tag.put("5", tags_5);
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
