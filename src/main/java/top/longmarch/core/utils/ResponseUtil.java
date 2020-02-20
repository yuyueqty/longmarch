package top.longmarch.core.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ResponseUtil {

    public static void respondJson(HttpServletRequest request,
            HttpServletResponse response, Integer code, String message) {
        respondJson(response, code, message, null);
    }

    public static void respondJson(
            HttpServletResponse response, Integer code, String message) {
        respondJson(response, code, message, null);
    }

    public static void respondJson(
            HttpServletResponse response, Integer code, String message, Object data) {
        respondJson(response, 200, code, message, data);
    }

    public static void respondJson(
            HttpServletResponse response, int respondStatus, int code, String message, Object data) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
        //这句话的意思，是让浏览器用utf8来解析返回的数据
        response.setHeader("Content-type", "application/json; charset=utf-8");
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");
        //设置请求结果响应码
        response.setStatus(respondStatus);
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
            String json = new ObjectMapper().writeValueAsString(map);
            printWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (printWriter != null)
                printWriter.close();
        }
    }

}
