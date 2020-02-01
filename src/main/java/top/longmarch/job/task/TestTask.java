package top.longmarch.job.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("testTask")
public class TestTask {

    private static final Logger log = LoggerFactory.getLogger(TestTask.class);

    public void test() {
        log.info("无参测试任务执行时间：{}", new Date());
    }

    public void say(String name) {
        log.info("带参测试任务执行时间：date={}, name={}", new Date(), name);
    }

}
