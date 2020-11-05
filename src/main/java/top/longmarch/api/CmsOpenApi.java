package top.longmarch.api;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import top.longmarch.cms.entity.Article;
import top.longmarch.cms.service.IArticleService;
import top.longmarch.cms.service.ITagService;

import java.util.Date;

@Api(value = "开放接口", tags = "文章预览页面")
@Controller
@RequestMapping("/api")
public class CmsOpenApi {

    @Autowired
    private IArticleService articleService;
    @Autowired
    private ITagService tagService;

    @ApiOperation(value = "文章详情")
    @RequestMapping("/article/{id}.html")
    private String getArticleById(@PathVariable Long id, Model model) {
        Article article = articleService.getById(id);
        if (article == null) {
            article = new Article();
            article.setTitle("文章不存在！！！");
            article.setContent("不要拿不存在的文章ID再瞎胡搞了~");
            article.setCreateTime(new Date());
            article.setClicks(0);
            article.setTags("无");
        } else {
            if (article.getPublishStatus() != 3) {
                article.setContent("文章未发布");
            }
            if (StrUtil.isNotBlank(article.getTags())) {
                String tagNames = tagService.getTagNames(article.getTags());
                article.setTags(tagNames);
            } else {
                article.setTags("无");
            }
        }
        updateClicks(article);
        model.addAttribute("article", article);
        return "/article";
    }

    private void updateClicks(Article article) {
        ThreadUtil.execute(new Runnable() {
            @Override
            public void run() {
                Article updateArticle =  new Article();
                updateArticle.setId(article.getId());
                updateArticle.setClicks(article.getClicks() + 1);
                articleService.updateById(updateArticle);
            }
        });
    }

}
