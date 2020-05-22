package top.longmarch;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.bean.result.WxMpUserList;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import top.longmarch.wx.entity.GzhUser;

import java.util.List;

public class WxTest2 {

    public static void main(String[] args) throws WxErrorException {
//        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
//        config.setAppId("wxaeeab59bae7d0deb");
//        config.setSecret("0c95e460f74fc1e3c4e56a825c8a385e");
//        WxMpService wxMpService = new WxMpServiceImpl();
//        wxMpService.setWxMpConfigStorage(config);
//
//        String accessToken = wxMpService.getAccessToken();
//        String url = "https://mp.weixin.qq.com/cgi-bin/settingpage?t=setting/index&action=index&token="+accessToken+"&lang=zh_CN&f=json";
//
//        String s = wxMpService.get(url, null);
//        System.out.println(s);

        fun();

        String time = "1574066601";
        DateTime date = DateUtil.date(Long.valueOf(time));
        System.out.println(date);
    }

    public static void fun4() throws Exception {
        fun();
    }

    public static void fun() throws WxErrorException {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId("wxaeeab59bae7d0deb");
        config.setSecret("0c95e460f74fc1e3c4e56a825c8a385e");
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);
        WxMpUserList userList = wxMpService.getUserService().userList("oG-Lc1E8kMH--SxsdhJzzcfrT73M");
        String nextOpenid = userList.getOpenids().get(userList.getOpenids().size() - 1);
//        List<String> openidList = (List<String>) userList
//        System.out.println(openidList);

//        String openid = openidList.get(openidList.size()-3);
        getUser(wxMpService, nextOpenid);
    }

    private static void getUser(WxMpService wxMpService, String openid) throws WxErrorException {
        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(openid);
        System.out.println(JSONUtil.toJsonStr(wxMpUser));
    }


    private static void fun2() throws Exception {
        GzhUser user = new GzhUser();
        user.setHeadImgUrl("http://thirdwx.qlogo.cn/mmopen/LIUI5tJGiauBPicodTwI8aZAibGbeCdc0zZYSJzlYyhprZoE2gfL7smHp18txEOqTWdP3IiaQvjnAzDhvJ8QFHxesQ/132");
        user.setNickname("王奕 Aviva");
        user.setSex(2);
        user.setCity("上海");
        user.setProvince("上海");
        user.setCountry("中国");

        String appId = "619125947727085568";
        String appKey = "5a85862f532c43d0b089c81b4c296a07";
        String feeType = "0";
//        getTag(user, appId, appKey, feeType);

        syncWxUser();
    }

    public static void syncWxUser() throws Exception {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
//        config.setAppId("wx61649ec50fe75470");
//        config.setSecret("e278ec217c0f6a0366b3c1cc39324bec");

        config.setAppId("wxaeeab59bae7d0deb");
        config.setSecret("0c95e460f74fc1e3c4e56a825c8a385e");

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);

//        List<String> openidList = wxMpService.getUserService().userList(null).getOpenids();
//        List<WxMpUser> userInfoList = wxMpService.getUserService().userInfoList(openidList);

//        for (WxMpUser wxMpUser : userInfoList) {
//            System.out.println(JSONUtil.toJsonStr(wxMpUser));
//        }

//        String nextOpenid = wxMpService.getUserService().userList(null).getNextOpenid();
//        System.out.println(nextOpenid);
//        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(nextOpenid);
//        System.out.println(wxMpUser.getNickname());

        List<String> openids = wxMpService.getUserService().userList("oG-Lc1MMP2uRwJ4yxP0r3Xi5-RiI").getOpenids();
        System.out.println(openids);


//        List<String> nextOpenid = wxMpService.getUserService().userList(null).getOpenids();
//        System.out.println(nextOpenid.get(0));
//        WxMpUser wxMpUser = wxMpService.getUserService().userInfo(nextOpenid.get(0));
//        System.out.println(wxMpUser.getNickname());

//        WxMpUserList wxMpUserList = wxMpService.getUserService().userList(nextOpenid.get(0));
//        List<String> openids = wxMpUserList.getOpenids();
//        System.out.println(openids);
    }

    public static void getTag(GzhUser user, String appId, String appKey, String feeType) {
        String url = "http://api.unidt.com/api/fractal/openid/report?app_id=" + appId + "&app_key=" + appKey + "&fee_type=" + feeType;
        JSONObject requestBody = new JSONObject();
        requestBody.put("image_url", user.getHeadImgUrl());
        requestBody.put("nickname", user.getNickname());
        requestBody.put("sex", user.getSex());
        requestBody.put("city", user.getCity());
        requestBody.put("province", user.getProvince());
        requestBody.put("country", user.getCountry());
        requestBody.put("url_used", "1");
        requestBody.put("image", "base64");
        requestBody.put("field", feeType);

//        String post = HttpUtil.post(url, requestBody.toString());
//        FwTagDTO fwTagDTO = JSONUtil.toBean(post, FwTagDTO.class);
//        System.out.println(fwTagDTO.getResult());
//        System.out.println(JSONUtil.toJsonStr(fwTagDTO));
    }

}
