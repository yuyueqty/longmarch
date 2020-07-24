package top.longmarch.douyin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.douyin.entity.DouyinAccount;
import top.longmarch.douyin.request.AuthRequestFactory;
import top.longmarch.douyin.service.AuthRequestService;
import top.longmarch.douyin.service.DouyinAccountService;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthRequestServiceImpl implements AuthRequestService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private DouyinAccountService douyinAccountService;

    @Override
    public String authorizeUrl(String source) {
        return AuthRequestFactory.getAuthRequest(source).authorize(AuthStateUtils.createState());
    }

    @Override
    public String callbackUrl(HttpServletResponse response, AuthCallback authCallback, String source) {
        AuthResponse authResponse = AuthRequestFactory.getAuthRequest(source).login(authCallback);
        if (authResponse != null && authResponse.ok()) {
            AuthUser authUser = (AuthUser) authResponse.getData();
            saveToken(authUser);
            return "https://tiaoyue.longmarch.top/douyin/account";
        } else {
            System.out.println("授权失败");
            return "http://127.0.0.1:8081";
        }
    }

    @Override
    public AuthUser getAuthUser(String token) {
        System.out.println("===UUID====>" + token);
        String authUserJson = stringRedisTemplate.opsForValue().get(token);
        if (StrUtil.isBlank(authUserJson)) {
            return null;
        }
        return JSONUtil.toBean(authUserJson, AuthUser.class);
    }

    @Override
    public AuthToken getAuthToken(String token) {
        AuthToken authToken = getAuthUser(token).getToken();
        // 校验accessToken是否过期, expireIn单位是秒
        int expireIn = authToken.getExpireIn();

        return authToken;
    }

    @Override
    public void logout(String token) {
        stringRedisTemplate.delete(token);
    }

    @Override
    public AuthResponse refresh(String source, String token) {
        AuthResponse authResponse = AuthRequestFactory.getAuthRequest(source).refresh(getAuthToken(token));
        return authResponse;
    }

    private void saveToken(AuthUser authUser) {
        douyinAccountService.saveOrUpdate(convert(authUser));
        TokenUtil.set(authUser.getToken());
    }

    private DouyinAccount convert(AuthUser authUser) {
        DouyinAccount douyinAccount = new DouyinAccount();
        AuthToken token = authUser.getToken();
        JSONObject rawUserInfo = authUser.getRawUserInfo();
        douyinAccount.setOpenId(token.getOpenId());
        douyinAccount.setUnionId(token.getUnionId());
        douyinAccount.setNickname(authUser.getNickname());
        douyinAccount.setSource(authUser.getSource());
        douyinAccount.setLocation(authUser.getLocation());
        douyinAccount.setAvatar(authUser.getAvatar());
        douyinAccount.setAvatarLarger(rawUserInfo.getString("avatar_larger"));
        douyinAccount.setGender(rawUserInfo.getInteger("gender"));
        douyinAccount.setGenderStr(authUser.getGender().getDesc());
        douyinAccount.setCountry(rawUserInfo.getString("country"));
        douyinAccount.setProvince(rawUserInfo.getString("province"));
        douyinAccount.setCity(rawUserInfo.getString("city"));
        douyinAccount.setEAccountRole(rawUserInfo.getString("e_account_role"));
        douyinAccount.setScope(authUser.getToken().getScope());
        douyinAccount.setExpireIn(authUser.getToken().getExpireIn());
        douyinAccount.setAccessToken(authUser.getToken().getAccessToken());
        douyinAccount.setRefreshToken(authUser.getToken().getRefreshToken());
        return douyinAccount;
    }

}
