package com;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import top.longmarch.core.utils.ResponseUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAction implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String token = request.getHeader("token");
        if (StrUtil.isBlank(token)) {
            ResponseUtil.respondJson(response, 500, 200, "token 无效", null);
        }
        // 根据token, 从Redis中获取登录用户信息


        filterChain.doFilter(servletRequest, servletResponse);
    }

}
