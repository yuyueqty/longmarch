package top.longmarch.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.longmarch.cms.dao.ArticleTagRelDao;
import top.longmarch.cms.entity.ArticleTagRel;
import top.longmarch.cms.service.IArticleTagRelService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 文章关联标签 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-02-28
 */
@Service
public class ArticleTagRelServiceImpl extends ServiceImpl<ArticleTagRelDao, ArticleTagRel> implements IArticleTagRelService {

    @Transactional
    @Override
    public void updateArticleTagRel(Long articleId, List<Long> tagIds) {
        List<Long> insertTagIds = null;
        List<Long> deleteTagIds = null;
        if (tagIds != null && tagIds.size() > 0) {
            List<ArticleTagRel> dhArticleTagRelList = this.list(new LambdaQueryWrapper<ArticleTagRel>().eq(ArticleTagRel::getArticleId, articleId));
            if (dhArticleTagRelList == null || dhArticleTagRelList.size() == 0) {
                insertTagIds = tagIds;
            } else {
                List<Long> dhTags = dhArticleTagRelList.stream().map(ArticleTagRel::getTagId).collect(Collectors.toList());
                insertTagIds = tagIds.stream().filter(id -> !dhTags.contains(id)).collect(Collectors.toList());
                deleteTagIds = dhTags.stream().filter(id -> !tagIds.contains(id)).collect(Collectors.toList());
            }
        }
        // 删除
        if (deleteTagIds != null && deleteTagIds.size() > 0) {
            this.remove(new LambdaQueryWrapper<ArticleTagRel>()
                    .eq(ArticleTagRel::getArticleId, articleId)
                    .in(ArticleTagRel::getTagId, deleteTagIds));
        }
        // 创建
        if (insertTagIds != null && insertTagIds.size() > 0) {
            List<ArticleTagRel> insertArticleTagRelList = insertTagIds.stream().map(tagId -> {
                ArticleTagRel articleTagRel = new ArticleTagRel();
                articleTagRel.setArticleId(articleId);
                articleTagRel.setTagId(tagId);
                return articleTagRel;
            }).collect(Collectors.toList());
            this.saveBatch(insertArticleTagRelList);
        }
    }

}
