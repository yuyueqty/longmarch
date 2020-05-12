//package top.longmarch;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
//import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
//import cn.hutool.core.util.StrUtil;
//import cn.hutool.db.Db;
//import cn.hutool.db.Entity;
//import cn.hutool.http.HttpUtil;
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.shiro.crypto.hash.Hash;
//import org.springframework.beans.BeanUtils;
//import top.longmarch.wx.controller.BuildOpenIdDataUtil;
//import top.longmarch.wx.entity.FwTag;
//import top.longmarch.wx.entity.FwTagDTO;
//import top.longmarch.wx.entity.GzhAccount;
//import top.longmarch.wx.entity.GzhUser;
//import top.longmarch.wx.entity.export.*;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.*;
//import java.util.stream.Collectors;
//
//public class ExportTest {
//
//    private static final String OPENID_URL = "http://api.unidt.com/api/fractal/openid/report?app_id=%s&app_key=%s&fee_type=%s";
//
//    public static void main2(String[] args) {
//        Map<String, Integer> user = new HashMap<>();
//        user.put("aaa", 80);
//        user.put("bbb", 90);
//        user.put("ccc", 80);
//        Map<String, Integer> rule = new HashMap<>();
//        rule.put("aaa", 90);
//        rule.put("bbb", 80);
//        rule.put("ccc", 90);
//
//        String s = rule.keySet().stream().findFirst().filter(k -> user.get(k) < rule.get(k)).get();
//        List<String> collect = rule.keySet().stream().filter(k -> user.get(k) < rule.get(k)).collect(Collectors.toList());
//
//        System.out.println(s);
//        System.out.println(collect);
//    }
//
//    public static void main(String[] args) throws Exception {
//        List<UserExportV2> userTagExportList = getUserExportV2s();
//        getNewTag(userTagExportList);
//
////        List<ExcelExportEntity> entity = new ArrayList<>();
////        List<Map<String, Object>> list = new ArrayList<>();
////        ExportParams exportParams = new ExportParams("微信用户分维标签", "用户标签");
////        try {
////            BuildOpenIdDataUtil.buildMapDataV2(entity, list, "5", userTagExportList);
////
////            Workbook workbook = ExcelExportUtil.exportExcel(exportParams,
////                    entity, list);
////            workbook.write(new FileOutputStream(new File("d:/users.xls")));
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//
//    }
//
//    private static void getNewTag(List<UserExportV2> list) {
//        Map<String, Integer> rule = getMapList2();
//        for (UserExportV2 user : list) {
//            getTag(user, rule);
//        }
//    }
//
//    private static void getTag(UserExportV2 user, Map<String, Integer> ruleMap) {
//        Map<String, Integer> userMap = user.getTags().stream().collect(Collectors.toMap(TagExportV2::getName, TagExportV2::getScore));
//        if (userMap.keySet().containsAll(ruleMap.keySet())) {
//            Optional<String> result = ruleMap.keySet().stream().findFirst().filter(k -> userMap.get(k) < ruleMap.get(k));
//            if (!result.isPresent()) {
//                System.out.println(String.format("%s 优秀员工", user.getNickname()));
//            }
//        }
//    }
//
//    private static Map<String, Integer> getMapList2() {
//        Map<String, Integer> map = new HashMap<>();
//        map.put("b260", 80);
//        map.put("b58", 20);
//        map.put("b60", 85);
//        map.put("b62", 10);
//        map.put("b640", 85);
//        map.put("b676", 85);
//        map.put("b732", 20);
//        map.put("b98", 80);
//        map.put("b99", 8);
//        map.put("E", 70);
//        map.put("r1110", 25);
//        map.put("r1160", 60);
//        map.put("r1164", 60);
//        map.put("r1654", 40);
//        map.put("r1685", 35);
//        map.put("r1921", 30);
//        map.put("r6467", 20);
//        map.put("tb978", 18);
//
//        return map;
//    }
//
//    private static List<Map<String, Object>> getMapList() {
//        List<Map<String, Object>> mapList = new ArrayList<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "b260");
//        map.put("score", 60);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b58");
//        map.put("score", 50);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b60");
//        map.put("score", 60);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b62");
//        map.put("score", 30);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b640");
//        map.put("score", 80);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b676");
//        map.put("score", 80);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b732");
//        map.put("score", 20);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b98");
//        map.put("score", 60);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "b99");
//        map.put("score", 50);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "E");
//        map.put("score", 40);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "r1110");
//        map.put("score", 40);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "r1160");
//        map.put("score", 50);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "r1164");
//        map.put("score", 60);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "r1654");
//        map.put("score", 65);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "r1685");
//        map.put("score", 70);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "r1921");
//        map.put("score", 60);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "r6467");
//        map.put("score", 60);
//        mapList.add(map);
//        map = new HashMap<>();
//        map.put("name", "tb978");
//        map.put("score", 60);
//        mapList.add(map);
//        return mapList;
//    }
//
//    private static List<UserExportV2> getUserExportV2s() throws SQLException {
//        List<UserExportV2> userTagExportList = new ArrayList<>();
//        for (GzhUser user : getOpends()) {
//            System.out.println(String.format("用户【%s】标签列表", user.getNickname()));
//            UserExportV2 userExportV2 = new UserExportV2();
//            List<TagExportV2> tagExportV2List = new ArrayList<>();
//            FwTagDTO fwTagDTO = analyseGzhUserTag(user);
//            for (FwTag fwTag : fwTagDTO.getResult()) {
//                TagExportV2 tagExportV2 = new TagExportV2();
//                BeanUtils.copyProperties(fwTag, tagExportV2);
//                tagExportV2List.add(tagExportV2);
//                System.out.println(JSONUtil.toJsonStr(tagExportV2));
//            }
//            userExportV2.setTags(tagExportV2List);
//            BeanUtils.copyProperties(user, userExportV2);
//            userTagExportList.add(userExportV2);
//        }
//        return userTagExportList;
//    }
//
//    private static List<GzhUser> getOpends() throws SQLException {
//        String[] opends = {
//                "o4ZrSwADySIOQyMY3HnF7B9o5_j4",
//                "o4ZrSwKSM0R3WMYFT_YNNzNR2_pk",
//                "o4ZrSwKfRMFy9LJflNaNmz9JcY78",
//                "o4ZrSwEbFFBKK5rfQpxJjuKAj6AM",
//                "o4ZrSwIquKlgSoK2rSQwbqcukRCs",
//                "o4ZrSwF0EfMg4NHjdqlMf8D7-Vl8",
//                "o4ZrSwEQ671yqdAVvXzD7Rt1UdUE",
//                "o4ZrSwPLxjaRPEuRkThoMGRrVhxI",
//                "o4ZrSwAh-Siw6Q-W8gXV145vxs5U",
//                "o4ZrSwBZlaIEbe0_6Vefm-NG4p10",
//                "o4ZrSwIM-4nOrVJvqVz-OmnNI1nI"
//        };
//
//        StringBuffer select = new StringBuffer();
//        select.append("select * from wx_gzh_user where open_id in (");
//        for (int i = 0; i < opends.length; i++) {
//            select.append("?");
//            if (i != (opends.length - 1)) {
//                select.append(",");
//            }
//        }
//        select.append(")");
//
//        List<Entity> result = Db.use().query(select.toString(), opends);
//        List<GzhUser> collect = result.stream().map(r -> {
//            GzhUser user = new GzhUser();
//            user.setOpenId(r.getStr("open_id"));
//            user.setNickname(r.getStr("nickname"));
//            user.setHeadImgUrl(r.getStr("head_img_url"));
//            user.setSexDesc(r.getStr("sex_desc"));
//            user.setSex(r.getInt("sex"));
//            user.setCountry(r.getStr("country"));
//            user.setProvince(r.getStr("province"));
//            user.setCity(r.getStr("city"));
//            return user;
//        }).collect(Collectors.toList());
//        return collect;
//    }
//
//    private static FwTagDTO analyseGzhUserTag(GzhUser user) {
//        String url = "http://106.75.16.57:8181/score";
//        JSONObject requestBody = new JSONObject();
//        requestBody.put("image_url", user.getHeadImgUrl());
//        requestBody.put("nickname", user.getNickname());
//        requestBody.put("sex", user.getSex());
//        requestBody.put("city", user.getCity());
//        requestBody.put("province", user.getProvince());
//        requestBody.put("country", user.getCountry());
//        requestBody.put("url_used", "1");
//        requestBody.put("image", "base64");
//
//        String post = HttpUtil.post(url, requestBody.toString());
//        FwTagDTO fwTagDTO = JSONUtil.toBean(post, FwTagDTO.class);
//        if (fwTagDTO.getCode() == 200) {
//            return fwTagDTO;
//        }
//        return null;
//    }
//
//    private static void fun4_1() {
//        List<ExcelExportEntity> entity = new ArrayList<>();
//        List<Map<String, Object>> list = new ArrayList<>();
//
//        List<String> fieldList = getFieldList();
//
//        List<UserTagExport> userTagExportList = getUserTagExports();
//
//        fun4(entity, list, fieldList, userTagExportList);
//        System.out.println(JSONUtil.toJsonStr(list));
//        try {
//            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("微信用户分维标签", "用户标签"),
//                    entity, list);
//            workbook.write(new FileOutputStream(new File("d:/users.xls")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static List<String> getFieldList() {
//        List<String> fieldList = new ArrayList<>();
//        fieldList.add("抗压(等级):rank_0");
//        fieldList.add("抗压(评分):score_0");
//        fieldList.add("执行力(等级):rank_1:15");
//        fieldList.add("执行力(评分):score_1:15");
//        return fieldList;
//    }
//
//    private static void fun4(List<ExcelExportEntity> entity,
//                             List<Map<String, Object>> list,
//                             List<String> fieldList,
//                             List<UserTagExport> userTagExportList) {
//        entity.add(new ExcelExportEntity("OpenID", "openId"));
//        entity.add(new ExcelExportEntity("昵称", "nickname"));
//        entity.add(new ExcelExportEntity("头像", "headImgUrl"));
//        entity.add(new ExcelExportEntity("性别", "sexDesc"));
//        entity.add(new ExcelExportEntity("国家", "country"));
//        entity.add(new ExcelExportEntity("省份", "province"));
//        entity.add(new ExcelExportEntity("城市", "city"));
//
//        for (int i = 0; i < fieldList.size(); i++) {
//            String field = fieldList.get(i);
//            String[] split = field.split(":");
//            if (split.length == 3) {
//                entity.add(new ExcelExportEntity(split[0], split[1], Integer.valueOf(split[2])));
//            } else {
//                entity.add(new ExcelExportEntity(split[0], split[1]));
//            }
//        }
//
//        Map<String, Object> map;
//        for (UserTagExport userTagExport : userTagExportList) {
//            map = new HashMap();
//            UserExport userExport = userTagExport.getUserExport();
//            map.put("openId", userExport.getOpenId());
//            map.put("nickname", userExport.getNickname());
//            map.put("headImgUrl", userExport.getHeadImgUrl());
//            map.put("sexDesc", userExport.getSexDesc());
//            map.put("country", userExport.getCountry());
//            map.put("province", userExport.getProvince());
//            map.put("city", userExport.getCity());
//
//            List<TagExport> tagExportList = userTagExport.getTagExportList();
//            for (int i = 0; i < tagExportList.size(); i++) {
//                TagExport tagExport = tagExportList.get(i);
//                map.put("rank_" + i, tagExport.getRank());
//                map.put("score_" + i, tagExport.getScore());
//            }
//            list.add(map);
//        }
//    }
//
//    private static void fun3() throws Exception {
//
//
//        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
//        entity.add(new ExcelExportEntity("姓名", "name"));
//
//        entity.add(new ExcelExportEntity("抗压(等级)", "rank_1"));
//        entity.add(new ExcelExportEntity("抗压(评分)", "score_1"));
//
//        entity.add(new ExcelExportEntity("执行力(等级)", "rank_2", 15));
//        entity.add(new ExcelExportEntity("执行力(评分)", "score_2", 15));
//
//        List<Map<String, Object>> list = new ArrayList();
//        Map<String, Object> map = new HashMap();
//        map.put("name", "张三");
//        map.put("rank_1", "高");
//        map.put("score_1", 90);
//        map.put("rank_2", "低");
//        map.put("score_2", 40);
//        list.add(map);
//
//        map = new HashMap();
//        map.put("name", "李四");
//        map.put("rank_1", "高");
//        map.put("score_1", 90);
//        map.put("rank_2", "低");
//        map.put("score_2", 40);
//        list.add(map);
//
//        ExportParams params = new ExportParams("2412312", "测试", ExcelType.HSSF);
//
//        Workbook workbook = ExcelExportUtil.exportExcel(params,
//                entity, list);
//        workbook.write(new FileOutputStream(new File("d:/users.xls")));
//    }
//
//    private static void fun2() throws Exception {
//        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();
//        ExcelExportEntity excelentity = new ExcelExportEntity("姓名", "name");
//        excelentity.setNeedMerge(true);
//        entity.add(excelentity);
//        entity.add(new ExcelExportEntity("性别", "sex"));
//        excelentity = new ExcelExportEntity(null, "students");
//        List<ExcelExportEntity> temp = new ArrayList<ExcelExportEntity>();
//        temp.add(new ExcelExportEntity("姓名", "name"));
//        temp.add(new ExcelExportEntity("性别", "sex"));
//        excelentity.setList(temp);
//        entity.add(excelentity);
//
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        Map<String, Object> map;
//        for (int i = 0; i < 10; i++) {
//            map = new HashMap<>();
//            map.put("name", "1" + i);
//            map.put("sex", "2" + i);
//
//            List<Map<String, Object>> tempList = new ArrayList<Map<String, Object>>();
//            tempList.add(map);
//            tempList.add(map);
//            map.put("students", tempList);
//
//            list.add(map);
//        }
//
//        ExportParams params = new ExportParams("2412312", "测试", ExcelType.XSSF);
//
//        Workbook workbook = ExcelExportUtil.exportExcel(params,
//                entity, list);
//        workbook.write(new FileOutputStream(new File("d:/users.xls")));
//
//    }
//
//    private static void fun1() {
//        List<UserTagExport> userTagExportList = getUserTagExports();
//
//        try {
//            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("微信用户分维标签", "用户标签"),
//                    UserTagExport.class, userTagExportList);
//            workbook.write(new FileOutputStream(new File("d:/users.xls")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static List<UserTagExport> getUserTagExports() {
//        List<UserTagExport> userTagExportList = new ArrayList<>();
//
//        UserTagExport userTagExport = new UserTagExport();
//        UserExport userExport = new UserExport();
//        userExport.setOpenId("oG-Lc1E8kMH--SxsdhJzzcfrT73M");
//        userExport.setNickname("暗•\uD83C\uDFC2");
//        userExport.setSexDesc("男");
//        userExport.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaELBWntdvOMrCPPto4jUHL8uo5uhUXEKrU9hljWC0PqJWwBfficL3B1YwWeBbAqhSiat3kVJsRTibGINQ/132");
//        userExport.setCountry("中国");
//        userExport.setProvince("上海");
//        userExport.setCity("上海");
//        userTagExport.setUserExport(userExport);
//
//        List<TagExport> tagExportList = new ArrayList<>();
//        TagExport tagExport = new TagExport();
//        tagExport.setName("r2309");
//        tagExport.setContent("抗压");
//        tagExport.setRank("中");
//        tagExport.setScore(50);
//        tagExportList.add(tagExport);
//        tagExport = new TagExport();
//        tagExport.setName("tb100");
//        tagExport.setContent("执行力");
//        tagExport.setRank("中");
//        tagExport.setScore(45);
//        tagExportList.add(tagExport);
//        userTagExport.setTagExportList(tagExportList);
//
//        userTagExportList.add(userTagExport);
//
//
//        userTagExport = new UserTagExport();
//        userExport = new UserExport();
//        userExport.setOpenId("oG-Lc1E8kMH--SxsdhJzzcfrT73M");
//        userExport.setNickname("大男人");
//        userExport.setSexDesc("男");
//        userExport.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaELBWntdvOMrCPPto4jUHL8uo5uhUXEKrU9hljWC0PqJWwBfficL3B1YwWeBbAqhSiat3kVJsRTibGINQ/132");
//        userExport.setCountry("中国");
//        userExport.setProvince("上海");
//        userExport.setCity("上海");
//        userTagExport.setUserExport(userExport);
//
//        tagExportList = new ArrayList<>();
//        tagExport = new TagExport();
//        tagExport.setName("r2309");
//        tagExport.setContent("抗压");
//        tagExport.setRank("中");
//        tagExport.setScore(50);
//        tagExportList.add(tagExport);
//        tagExport = new TagExport();
//        tagExport.setName("tb100");
//        tagExport.setContent("执行力");
//        tagExport.setRank("中");
//        tagExport.setScore(45);
//        tagExportList.add(tagExport);
//        userTagExport.setTagExportList(tagExportList);
//
//        userTagExportList.add(userTagExport);
//        return userTagExportList;
//    }
//
//
//}
