package top.longmarch.cms.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.longmarch.cms.dao.ArticleDao;
import top.longmarch.cms.entity.Article;
import top.longmarch.cms.service.IArticleService;
import top.longmarch.core.utils.SummaryExtractorUtil;
import top.longmarch.core.utils.UserUtil;

import java.util.List;
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
                List<String> list = SummaryExtractorUtil.getDuanYu(article.getContent());
                String result = list.stream().collect(Collectors.joining(","));
                article.setLabel(result);
            }
        }
        this.saveOrUpdate(article);
    }

}
