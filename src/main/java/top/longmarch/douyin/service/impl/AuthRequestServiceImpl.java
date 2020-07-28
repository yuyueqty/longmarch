package top.longmarch.douyin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.longmarch.core.utils.TokenUtil;
import top.longmarch.core.utils.UserUtil;
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
    public void logout(String token) {
        stringRedisTemplate.delete(token);
    }

    @Override
    @Transactional
    public void refreshToken(String source, String openId) {
        DouyinAccount douyinAccount = douyinAccountService.getOne(new LambdaQueryWrapper<DouyinAccount>()
                .eq(DouyinAccount::getOpenId, openId).eq(DouyinAccount::getCreateBy, UserUtil.getUserId()));
        if (douyinAccount != null) {
            AuthToken authToken = new AuthToken();
            BeanUtils.copyProperties(douyinAccount, authToken);
            AuthResponse authResponse = AuthRequestFactory.getAuthRequest(source).refresh(authToken);
            if (authResponse.ok()) {
                JSONObject jsonObject = JSONUtil.parseObj(authResponse.getData());
                douyinAccount = new DouyinAccount();
                douyinAccount.setOpenId(openId);
                douyinAccount.setAccessToken(jsonObject.getStr("accessToken"));
                douyinAccount.setRefreshToken(jsonObject.getStr("refreshToken"));
                douyinAccount.setExpireIn(jsonObject.getInt("expireIn"));
                douyinAccount.setScope(jsonObject.getStr("scope"));
                douyinAccountService.saveOrUpdate(douyinAccount);
                douyinAccountService.setDefault(openId);
            } else {
                throw new RuntimeException("刷新失败：" + authResponse.getMsg());
            }
        }
    }

    private void saveToken(AuthUser authUser) {
        douyinAccountService.saveOrUpdate(convert(authUser));
        TokenUtil.set(authUser.getToken());
    }

    private DouyinAccount convert(AuthUser authUser) {
        DouyinAccount douyinAccount = new DouyinAccount();
        AuthToken token = authUser.getToken();
        if (StrUtil.isBlank(token.getOpenId())) {
            throw new RuntimeException("无效的用户");
        }
        douyinAccount.setOpenId(token.getOpenId());
        if (StrUtil.isNotBlank(token.getUnionId())) {
            douyinAccount.setUnionId(token.getUnionId());
        }
        if (StrUtil.isNotBlank(token.getScope())) {
            douyinAccount.setScope(token.getScope());
        }
        if (token.getExpireIn() > 0) {
            douyinAccount.setExpireIn(token.getExpireIn());
        }
        if (StrUtil.isNotBlank(token.getAccessToken())) {
            douyinAccount.setAccessToken(token.getAccessToken());
        }
        if (StrUtil.isNotBlank(token.getRefreshToken())) {
            douyinAccount.setRefreshToken(token.getRefreshToken());
        }
        if (StrUtil.isNotBlank(authUser.getNickname())) {
            douyinAccount.setNickname(authUser.getNickname());
        }
        if (StrUtil.isNotBlank(authUser.getSource())) {
            douyinAccount.setSource(authUser.getSource());
        }
        if (StrUtil.isNotBlank(authUser.getLocation())) {
            douyinAccount.setLocation(authUser.getLocation());
        }
        if (StrUtil.isNotBlank(authUser.getAvatar())) {
            douyinAccount.setAvatar(authUser.getAvatar());
        }
        JSONObject rawUserInfo = JSONUtil.parseObj(authUser.getRawUserInfo());
        if (rawUserInfo != null) {
            if (StrUtil.isNotBlank(rawUserInfo.getStr("avatar_larger"))) {
                douyinAccount.setAvatarLarger(rawUserInfo.getStr("avatar_larger"));
            }
            if (rawUserInfo.getInt("gender") != null) {
                douyinAccount.setGender(rawUserInfo.getInt("gender"));
            }
            if (StrUtil.isNotBlank(authUser.getGender().getDesc())) {
                douyinAccount.setGenderStr(authUser.getGender().getDesc());
            }
            if (StrUtil.isNotBlank(rawUserInfo.getStr("country"))) {
                douyinAccount.setCountry(rawUserInfo.getStr("country"));
            }
            if (StrUtil.isNotBlank(rawUserInfo.getStr("province"))) {
                douyinAccount.setProvince(rawUserInfo.getStr("province"));
            }
            if (StrUtil.isNotBlank(rawUserInfo.getStr("city"))) {
                douyinAccount.setCity(rawUserInfo.getStr("city"));
            }
            if (StrUtil.isNotBlank(rawUserInfo.getStr("e_account_role"))) {
                douyinAccount.setEAccountRole(rawUserInfo.getStr("e_account_role"));
            }
        }
        return douyinAccount;
    }

}
