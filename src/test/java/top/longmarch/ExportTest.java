package top.longmarch;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.json.JSONUtil;
import org.apache.poi.ss.usermodel.Workbook;
import top.longmarch.wx.entity.export.TagExport;
import top.longmarch.wx.entity.export.UserExport;
import top.longmarch.wx.entity.export.UserTagExport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportTest {


    public static void main(String[] args) throws Exception {
        fun4_1();
    }

    private static void fun4_1() {
        List<ExcelExportEntity> entity = new ArrayList<>();
        List<Map<String, Object>> list = new ArrayList<>();

        List<String> fieldList = getFieldList();

        List<UserTagExport> userTagExportList = getUserTagExports();

        fun4(entity, list, fieldList, userTagExportList);
        System.out.println(JSONUtil.toJsonStr(list));
        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("微信用户分维标签", "用户标签"),
                    entity, list);
            workbook.write(new FileOutputStream(new File("d:/users.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getFieldList() {
        List<String> fieldList = new ArrayList<>();
        fieldList.add("抗压(等级):rank_0");
        fieldList.add("抗压(评分):score_0");
        fieldList.add("执行力(等级):rank_1:15");
        fieldList.add("执行力(评分):score_1:15");
        return fieldList;
    }

    private static void fun4(List<ExcelExportEntity> entity,
                             List<Map<String, Object>> list,
                             List<String> fieldList,
                             List<UserTagExport> userTagExportList) {
        entity.add(new ExcelExportEntity("OpenID", "openId"));
        entity.add(new ExcelExportEntity("昵称", "nickname"));
        entity.add(new ExcelExportEntity("头像", "headImgUrl"));
        entity.add(new ExcelExportEntity("性别", "sexDesc"));
        entity.add(new ExcelExportEntity("国家", "country"));
        entity.add(new ExcelExportEntity("省份", "province"));
        entity.add(new ExcelExportEntity("城市", "city"));

        for (int i = 0; i < fieldList.size(); i++) {
            String field = fieldList.get(i);
            String[] split = field.split(":");
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

    private static void fun3() throws Exception {


        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        entity.add(new ExcelExportEntity("姓名", "name"));

        entity.add(new ExcelExportEntity("抗压(等级)", "rank_1"));
        entity.add(new ExcelExportEntity("抗压(评分)", "score_1"));

        entity.add(new ExcelExportEntity("执行力(等级)", "rank_2", 15));
        entity.add(new ExcelExportEntity("执行力(评分)", "score_2", 15));

        List<Map<String, Object>> list = new ArrayList();
        Map<String, Object> map = new HashMap();
        map.put("name", "张三");
        map.put("rank_1", "高");
        map.put("score_1", 90);
        map.put("rank_2", "低");
        map.put("score_2", 40);
        list.add(map);

        map = new HashMap();
        map.put("name", "李四");
        map.put("rank_1", "高");
        map.put("score_1", 90);
        map.put("rank_2", "低");
        map.put("score_2", 40);
        list.add(map);

        ExportParams params = new ExportParams("2412312", "测试", ExcelType.HSSF);

        Workbook workbook = ExcelExportUtil.exportExcel(params,
                entity, list);
        workbook.write(new FileOutputStream(new File("d:/users.xls")));
    }

    private static void fun2() throws Exception {
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
        ExcelExportEntity excelentity = new ExcelExportEntity("姓名", "name");
        excelentity.setNeedMerge(true);
        entity.add(excelentity);
        entity.add(new ExcelExportEntity("性别", "sex"));
        excelentity = new ExcelExportEntity(null, "students");
        List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
        temp.add(new ExcelExportEntity("姓名", "name"));
        temp.add(new ExcelExportEntity("性别", "sex"));
        excelentity.setList(temp);
        entity.add(excelentity);

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map;
        for (int i = 0; i < 10; i++) {
            map = new HashMap<String, Object>();
            map.put("name", "1" + i);
            map.put("sex", "2" + i);

            List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
            tempList.add(map);
            tempList.add(map);
            map.put("students", tempList);

            list.add(map);
        }

        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);

        Workbook workbook = ExcelExportUtil.exportExcel(params,
                entity, list);
        workbook.write(new FileOutputStream(new File("d:/users.xls")));

    }

    private static void fun1() {
        List<UserTagExport> userTagExportList = getUserTagExports();

        try {
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("微信用户分维标签", "用户标签"),
                    UserTagExport.class, userTagExportList);
            workbook.write(new FileOutputStream(new File("d:/users.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<UserTagExport> getUserTagExports() {
        List<UserTagExport> userTagExportList = new ArrayList<>();

        UserTagExport userTagExport = new UserTagExport();
        UserExport userExport = new UserExport();
        userExport.setOpenId("oG-Lc1E8kMH--SxsdhJzzcfrT73M");
        userExport.setNickname("暗•\uD83C\uDFC2");
        userExport.setSexDesc("男");
        userExport.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaELBWntdvOMrCPPto4jUHL8uo5uhUXEKrU9hljWC0PqJWwBfficL3B1YwWeBbAqhSiat3kVJsRTibGINQ/132");
        userExport.setCountry("中国");
        userExport.setProvince("上海");
        userExport.setCity("上海");
        userTagExport.setUserExport(userExport);

        List<TagExport> tagExportList = new ArrayList<>();
        TagExport tagExport = new TagExport();
        tagExport.setName("r2309");
        tagExport.setContent("抗压");
        tagExport.setRank("中");
        tagExport.setScore(50);
        tagExportList.add(tagExport);
        tagExport = new TagExport();
        tagExport.setName("tb100");
        tagExport.setContent("执行力");
        tagExport.setRank("中");
        tagExport.setScore(45);
        tagExportList.add(tagExport);
        userTagExport.setTagExportList(tagExportList);

        userTagExportList.add(userTagExport);


        userTagExport = new UserTagExport();
        userExport = new UserExport();
        userExport.setOpenId("oG-Lc1E8kMH--SxsdhJzzcfrT73M");
        userExport.setNickname("大男人");
        userExport.setSexDesc("男");
        userExport.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaELBWntdvOMrCPPto4jUHL8uo5uhUXEKrU9hljWC0PqJWwBfficL3B1YwWeBbAqhSiat3kVJsRTibGINQ/132");
        userExport.setCountry("中国");
        userExport.setProvince("上海");
        userExport.setCity("上海");
        userTagExport.setUserExport(userExport);

        tagExportList = new ArrayList<>();
        tagExport = new TagExport();
        tagExport.setName("r2309");
        tagExport.setContent("抗压");
        tagExport.setRank("中");
        tagExport.setScore(50);
        tagExportList.add(tagExport);
        tagExport = new TagExport();
        tagExport.setName("tb100");
        tagExport.setContent("执行力");
        tagExport.setRank("中");
        tagExport.setScore(45);
        tagExportList.add(tagExport);
        userTagExport.setTagExportList(tagExportList);

        userTagExportList.add(userTagExport);
        return userTagExportList;
    }


}
