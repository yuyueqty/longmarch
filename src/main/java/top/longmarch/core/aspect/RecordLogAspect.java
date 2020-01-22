package top.longmarch.core.aspect;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.config.ApplicationContextManager;
import top.longmarch.core.utils.UserUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RecordLogAspect implements AspectApi {

    private static final Logger logger = LoggerFactory.getLogger(RecordLogAspect.class);
    private HttpServletRequest request;
    private LogService logService;

    @Override
    public Object doHandlerAspect(ProceedingJoinPoint pjp, Method method)
            throws Throwable {
        Object proceed = pjp.proceed();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            String operationDetail = executeTemplate(log.value(), pjp, method);
            if (StrUtil.isBlank(operationDetail) && pjp.getArgs() != null) {
                operationDetail = JSONUtil.toJsonStr(pjp.getArgs());
            }
            Api api = AnnotationUtil.getAnnotation(pjp.getSignature().getDeclaringType(), Api.class);
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            Log.LogType type = log.type();
            switch (type) {
                case OPERATE:
                    saveOperateLog(api, apiOperation, operationDetail);
                    break;
                case LOGIN:
                    saveLoginLog(operationDetail);
                    break;
                default:
                    break;
            }
        }
        return proceed;
    }

    // 解析SPEL
    private String executeTemplate(String template, ProceedingJoinPoint joinPoint, Method method) {
        LocalVariableTableParameterNameDiscoverer parameterNameDiscovere =
                new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = parameterNameDiscovere.getParameterNames(method);

        EvaluationContext context = new StandardEvaluationContext();
        ExpressionParser parser = new SpelExpressionParser();
        Object[] args = joinPoint.getArgs();
        if (args.length == parameterNames.length) {
            for (int i = 0, len = args.length; i < len; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }
        return parser.parseExpression(template, new TemplateParserContext()).getValue(
                context, String.class);
    }

    private void saveOperateLog(Api api, ApiOperation apiOperation, String operationDetail) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                // TODO
                Map<String, Object> log = new HashMap<String, Object>();
                log.put("operateTime", new Date());
                log.put("busType", api != null ? api.value() : "");
                log.put("operateType", apiOperation != null ? apiOperation.value() : "");
                log.put("operateDetail", operationDetail);
                log.put("userId", UserUtil.loginUser().getId());
                log.put("userName", UserUtil.loginUser().getUsername());
                logService.saveOperateLog(log);
                logger.debug("操作日志：" + log);
            }
        });
    }

    private void saveLoginLog(String operationDetail) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                // TODO
                Map<String, Object> log = new HashMap<String, Object>();
                log.put("loginTime", new Date());
                log.put("userId", UserUtil.loginUser().getId());
                log.put("userName", UserUtil.loginUser().getUsername());
                log.put("userAgent", request.getHeader("User-Agent"));
                log.put("ip", getIPAddress(request));
                logService.saveLoginLog(log);
                logger.debug("登陆日志：" + log);
            }
        });

    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setLogService(LogService logService) {
        this.logService = logService;
    }

    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;

        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //Proxy-Client-IP：apache 服务代理
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ipAddresses != null && ipAddresses.length() != 0) {
            ip = ipAddresses.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
