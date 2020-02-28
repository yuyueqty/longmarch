package top.longmarch.cms.service;

import top.longmarch.cms.entity.ArticleTagRel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 文章关联标签 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-02-28
 */
public interface IArticleTagRelService extends IService<ArticleTagRel> {

    void updateArticleTagRel(Long articleId, List<Long> tagIds);
}
