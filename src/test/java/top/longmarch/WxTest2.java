package top.longmarch;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import top.longmarch.wx.entity.FwTagDTO;
import top.longmarch.wx.entity.GzhUser;

import java.util.List;

public class WxTest2 {


    public static void main(String[] args) throws Exception {
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
        getTag(user, appId, appKey, feeType);
    }

    public static void syncWxUser() throws WxErrorException {
        WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
        config.setAppId("wx61649ec50fe75470");
        config.setSecret("e278ec217c0f6a0366b3c1cc39324bec");

        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(config);

        List<String> openidList = wxMpService.getUserService().userList(null).getOpenids();
        List<WxMpUser> userInfoList = wxMpService.getUserService().userInfoList(openidList);

        for (WxMpUser wxMpUser : userInfoList) {
            System.out.println(JSONUtil.toJsonStr(wxMpUser));
        }
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

        String post = HttpUtil.post(url, requestBody.toString());
        FwTagDTO fwTagDTO = JSONUtil.toBean(post, FwTagDTO.class);
        System.out.println(fwTagDTO.getResult());
        System.out.println(JSONUtil.toJsonStr(fwTagDTO));
    }

}
