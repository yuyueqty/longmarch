package top.longmarch.douyin.controller;

import me.zhyd.oauth.model.AuthCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.service.AuthRequestService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DouYinOauthRequestController {

    @Autowired
    private AuthRequestService authRequestService;

    @RequestMapping("/{source}/loginUrl")
    public Result getRenderAuth(@PathVariable String source) {
        return Result.ok().add(authRequestService.authorizeUrl(source));
    }

    @RequestMapping("/{source}/login")
    public void login(HttpServletResponse response, @PathVariable String source) {
        try {
            response.sendRedirect(authRequestService.authorizeUrl(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/{source}/callback")
    public void callback(HttpServletResponse response, AuthCallback callback, @PathVariable String source) {
        try {
            response.sendRedirect(authRequestService.callbackUrl(response, callback, source));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/{source}/refresh")
    public void refresh(HttpServletResponse response, AuthCallback callback, @PathVariable String source) {
        authRequestService.refresh(source, "");
    }

    @RequestMapping("/logout")
    public Object logout(@RequestParam String token) {
        authRequestService.logout(token);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "ok");
        return result;
    }

    @RequestMapping("/error_msg")
    public Object error_msg() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "参数错误");
        return result;
    }

}
