package top.longmarch.core.config;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrossDomainFilter implements Filter {
    
    private static Set<String> allowedOrigins = new LinkedHashSet<>();
    static {
        allowedOrigins.add("http://localhost:9527");
        allowedOrigins.add("http://localhost:9528");
        allowedOrigins.add("http://localhost:8080");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String originHeader = httpRequest.getHeader("Origin");
//        if (allowedOrigins.contains(originHeader)) {
            httpResponse.setHeader("Access-Control-Allow-Origin", originHeader);
            httpResponse.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");
            httpResponse.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        }
        chain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
    }

}
