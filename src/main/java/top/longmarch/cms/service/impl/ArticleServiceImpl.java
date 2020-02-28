package top.longmarch.cms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HtmlUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hankcs.hanlp.HanLP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.longmarch.cms.dao.ArticleDao;
import top.longmarch.cms.entity.Article;
import top.longmarch.cms.service.IArticleService;
import top.longmarch.cms.service.ITagService;
import top.longmarch.core.utils.SummaryExtractorUtil;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.Parameter;
import top.longmarch.sys.service.IParameterService;

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
    @Autowired
    private ITagService tagService;
    @Autowired
    private IParameterService parameterService;

    @Override
    public IPage<Article> search(Page page, Map<String, Object> params) {
        return articleDao.search(page, params);
    }

    @Transactional
    @Override
    public void saveArticle(Article article) {
        Parameter sys_params = parameterService.getParameterByName("sys_params");
        JSONObject json = JSONUtil.parseObj(sys_params.getParamValue());
        if (StrUtil.isBlank(article.getImageUrl())) {
            article.setImageUrl(json.getStr("default_blog_img"));
        }
        if (article.getId() == null) {
            // 登录用户为默认作者
            if (StrUtil.isBlank(article.getAuthor())) {
                article.setAuthor(UserUtil.loginUser().getUsername());
            }
            // 文章发布时间大于当前时间时，系统默认为文章还未发布，将文章状态强制修改为草稿
            if (article.getPublishTime().after(new Date())) {
                article.setPublishStatus(1);
            }
            // 根据文章内容生成标签和摘要
            String content = HtmlUtil.cleanHtmlTag(article.getContent());
            // 自动生成摘要
            if (StrUtil.isBlank(article.getSummary())) {
                article.setSummary(SummaryExtractorUtil.getSummary(content));
            }
            // 自动生成标签
            if (StrUtil.isBlank(article.getTags())) {
                List<String> list = HanLP.extractKeyword(content, 5);
                String result = list.stream().collect(Collectors.joining(","));
                article.setTags(result);
            }
            this.save(article);
        }
        // 更新文章标签
        List<Long> tagIds = tagService.updateArticleTag(article.getId(), article.getTags());
        article.setTags(CollectionUtil.join(tagIds, ","));
        this.updateById(article);
    }

    /**
     * 用于定时任务批量处理
     */
    @Override
    public void batchPublishArticles() {
        // 发布时间小于等于当前时间，且发布状态必须为草稿
        articleDao.batchPublishArticles();
        log.info("定时更新文章状态执行完成");
    }

    @Transactional
    @Override
    public void removeArticleByIds(List<Long> ids) {
        if (CollectionUtil.isNotEmpty(ids)) {
            List<Article> articleList = this.list(new LambdaQueryWrapper<Article>().in(Article::getId, ids));
            if (CollectionUtil.isNotEmpty(articleList)) {
                tagService.removeArticleTagRel(articleList);
                this.removeByIds(ids);
            }
        }
    }

}
