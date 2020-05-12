//package top.longmarch;
//
//import cn.hutool.json.JSONUtil;
//import top.longmarch.wx.entity.WxUserTagRule;
//import top.longmarch.wx.entity.export.TagExportV2;
//import top.longmarch.wx.entity.export.UserExportV2;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class TagTest {
//
//    public static void main(String[] args) {
//        List<UserExportV2> userList = getUserList();
//        List<WxUserTagRule> ruleList = getRuleList();
//        for (UserExportV2 user : userList) {
//            Map<String, String> userTagMap = matching(user.getOpenId(), user.getTags(), ruleList);
//            System.out.println(JSONUtil.toJsonStr(userTagMap));
//        }
//    }
//
//    private static synchronized Map<String, String> matching(String openId, List<TagExportV2> tagList, List<WxUserTagRule> ruleList) {
//
//        Map<String, String> userTagMap = new HashMap<>();
//        for (WxUserTagRule wxUserTagRule : ruleList) {
//            boolean flag = true;
//            Map<String, String> collect = wxUserTagRule.getTagList().stream().collect(Collectors.toMap(TagExportV2::getName, TagExportV2::getRank));
////            System.out.println(JSONUtil.toJsonStr(collect));
//            for (TagExportV2 t : tagList) {
////                System.out.println(collect.get(t.getName()) + "-" + t.getRank());
//                if (!t.getRank().equals(collect.get(t.getName()))) {
//                    flag = false;
//                    break;
//                }
//            }
//            if (flag) {
//                userTagMap.put(wxUserTagRule.getRuleName(), openId);
//            }
//        }
//        return userTagMap;
//    }
//
//    private static List<UserExportV2> getUserList() {
//        List<UserExportV2> userList = new ArrayList<>();
//        List<TagExportV2> tagExportV2List = new ArrayList<>();
//        tagExportV2List.add(getTagExportV2("aaa", "高"));
//        tagExportV2List.add(getTagExportV2("bbb", "中"));
//        tagExportV2List.add(getTagExportV2("ccc", "高"));
//        userList.add(getUserExportV2("openid_1", "张三", tagExportV2List));
//
//        tagExportV2List = new ArrayList<>();
//        tagExportV2List.add(getTagExportV2("ddd", "高"));
//        tagExportV2List.add(getTagExportV2("eee", "中"));
//        tagExportV2List.add(getTagExportV2("fff", "高"));
//        userList.add(getUserExportV2("openid_2", "李四", tagExportV2List));
//
//        return userList;
//    }
//
//    private static List<WxUserTagRule> getRuleList() {
//        List<WxUserTagRule> ruleList = new ArrayList<>();
//        List<TagExportV2> tagExportV2List = new ArrayList<>();
//        tagExportV2List.add(getTagExportV2("aaa", "高"));
//        tagExportV2List.add(getTagExportV2("bbb", "中"));
//        tagExportV2List.add(getTagExportV2("ccc", "高"));
//        ruleList.add(getWxUserTagRule("第一个标签", tagExportV2List));
//
//        tagExportV2List = new ArrayList<>();
//        tagExportV2List.add(getTagExportV2("ddd", "高"));
//        tagExportV2List.add(getTagExportV2("eee", "中"));
//        tagExportV2List.add(getTagExportV2("fff", "高"));
//        ruleList.add(getWxUserTagRule("第二个标签", tagExportV2List));
//
//        return ruleList;
//    }
//
//    private static WxUserTagRule getWxUserTagRule(String ruleName, List<TagExportV2> tagExportV2List) {
//        WxUserTagRule wxUserTagRule = new WxUserTagRule();
//        wxUserTagRule.setRuleName(ruleName);
//        wxUserTagRule.setTagList(tagExportV2List);
//        return wxUserTagRule;
//    }
//
//    private static UserExportV2 getUserExportV2(String openId, String nickName, List<TagExportV2> tagExportV2List) {
//        UserExportV2 userExportV2 = new UserExportV2();
//        userExportV2.setOpenId(openId);
//        userExportV2.setNickname(nickName);
//        userExportV2.setTags(tagExportV2List);
//        return userExportV2;
//    }
//
//    private static TagExportV2 getTagExportV2(String name, String rank) {
//        TagExportV2 tagExportV2 = new TagExportV2();
//        tagExportV2.setName(name);
//        tagExportV2.setRank(rank);
//        return tagExportV2;
//    }
//
//}
