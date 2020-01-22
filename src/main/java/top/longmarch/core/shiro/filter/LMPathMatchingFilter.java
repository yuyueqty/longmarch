package top.longmarch.core.shiro.filter;

import top.longmarch.core.shiro.model.Commons;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LMPathMatchingFilter extends PathMatchingFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        if(Commons.isOpttons(request, response)) {
            return false;
        }
        return super.preHandle(request,response);
    }

}
