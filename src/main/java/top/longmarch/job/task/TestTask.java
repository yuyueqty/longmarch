package top.longmarch.job.task;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("testTask")
public class TestTask {

    public void test() {
        System.out.println("测试任务执行时间：" + new Date());
    }

    public void say(String name) {
        System.out.println("测试任务执行时间：" + new Date() + " name：" + name);
    }

}
