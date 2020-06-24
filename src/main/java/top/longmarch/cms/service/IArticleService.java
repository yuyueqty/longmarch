package top.longmarch.cms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.cms.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-30
 */
public interface IArticleService extends IService<Article> {

    IPage<Article> search(Page page, Map<String, Object> params);

    void saveArticle(Article article);

    void batchPublishArticles();

    void removeArticleByIds(List<Long> ids);

}
