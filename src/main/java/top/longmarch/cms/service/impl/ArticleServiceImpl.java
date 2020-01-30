package top.longmarch.cms.service.impl;

import top.longmarch.cms.entity.Article;
import top.longmarch.cms.dao.ArticleDao;
import top.longmarch.cms.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
