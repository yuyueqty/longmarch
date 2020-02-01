package top.longmarch.job.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.longmarch.cms.service.IArticleService;

@Component("longMarchJobTask")
public class LongMarchJobTask {

    @Autowired
    private IArticleService articleService;

    public void batchPublishArticles() {
        articleService.batchPublishArticles();
    }

}
