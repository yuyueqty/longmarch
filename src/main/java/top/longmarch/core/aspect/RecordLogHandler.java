package top.longmarch.core.aspect;

import javax.servlet.http.HttpServletRequest;

public class RecordLogHandler extends AspectHandler {

    HttpServletRequest request;
    private LogService logService;

    public RecordLogHandler(HttpServletRequest request, LogService logService) {
        this.request = request;
        this.logService = logService;
    }

    @Override
    protected AspectApi factoryMethod() {
        RecordLogAspect recordLogAspect = new RecordLogAspect();
        recordLogAspect.setRequest(request);
        recordLogAspect.setLogService(logService);
        return recordLogAspect;
    }

}
