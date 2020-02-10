package top.longmarch.cms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hankcs.hanlp.HanLP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.cms.dao.ArticleDao;
import top.longmarch.cms.entity.Article;
import top.longmarch.cms.service.IArticleService;
import top.longmarch.core.utils.SummaryExtractorUtil;
import top.longmarch.core.utils.UserUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-30
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, Article> implements IArticleService {

    private static final Logger log = LoggerFactory.getLogger(ArticleServiceImpl.class);
    @Autowired
    private ArticleDao articleDao;

    @Override
    public IPage<Article> search(Page page, Map<String, Object> params) {
        return articleDao.search(page, params);
    }

    @Override
    public void saveArticle(Article article) {
        if (article.getId() == null) {
            if (StrUtil.isBlank(article.getAuthor())) {
                article.setAuthor(UserUtil.loginUser().getUsername());
            }
            if (StrUtil.isBlank(article.getSummary())) {
                article.setSummary(SummaryExtractorUtil.getSummary(article.getContent()));
            }
            if (StrUtil.isBlank(article.getLabel())) {
                List<String> list = HanLP.extractKeyword(article.getContent(), 5);
                String result = list.stream().collect(Collectors.joining(","));
                article.setLabel(result);
            }
        }
        // 文章发布时间大于当前时间时，系统默认为文章还未发布，将文章状态强制修改为草稿
        if (article.getPublishTime().after(new Date())) {
            article.setPublishStatus(1);
        }
        this.saveOrUpdate(article);
    }

    @Override
    public void batchPublishArticles() {
        // 发布时间小于等于当前时间，且发布状态必须为草稿
        articleDao.batchPublishArticles();
        log.info("定时更新文章状态执行完成");
    }

}
