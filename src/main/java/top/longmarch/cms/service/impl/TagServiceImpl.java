package top.longmarch.cms.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.longmarch.cms.dao.TagDao;
import top.longmarch.cms.entity.Article;
import top.longmarch.cms.entity.ArticleTagRel;
import top.longmarch.cms.entity.Tag;
import top.longmarch.cms.service.IArticleTagRelService;
import top.longmarch.cms.service.ITagService;
import top.longmarch.core.exception.LongmarchException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 标签 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-02-27
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagDao, Tag> implements ITagService {

    @Autowired
    private IArticleTagRelService articleTagRelService;

    @Transactional
    @Override
    public void saveTag(Tag tag) {
        List<Tag> createTagList = null;
        List<String> repetitionTagList = null;
        if (StrUtil.isNotBlank(tag.getTagName())) {
            String[] split = tag.getTagName().split(",|，|\\|");
            if (split != null && split.length > 0) {
                // 标签去重
                Set<String> collect = Arrays.stream(split).collect(Collectors.toSet());
                createTagList = new ArrayList<>();
                repetitionTagList = new ArrayList<>();
                for (String tagName : collect) {
                    // 所有用户共享标签，这里不用过滤用户
                    int count = this.count(new LambdaQueryWrapper<Tag>().eq(Tag::getTagName, tagName));
                    if (count == 0) {
                        createTagList.add(new Tag(tagName));
                    } else {
                        repetitionTagList.add(tagName);
                    }
                }
            }
        }
        if (repetitionTagList != null && repetitionTagList.size() > 0) {
            throw new LongmarchException(String.format("重复标签[%s]", CollectionUtil.join(repetitionTagList, ",")));
        }
        // 批量创建标签
        if (createTagList != null && createTagList.size() > 0) {
            this.saveBatch(createTagList);
        }
    }

    @Transactional
    @Override
    public List<Long> updateArticleTag(Long articleId, String tagNames) {
        List<Tag> saveOrUpdateTagList = null;
        if (StrUtil.isNotBlank(tagNames)) {
            String[] split = tagNames.split(",|，|\\|");
            if (split != null && split.length > 0) {
                // 标签去重
                Set<String> collect = Arrays.stream(split).collect(Collectors.toSet());
                saveOrUpdateTagList = new ArrayList<>();
                for (String tagName : collect) {
                    // 所有用户共享标签，这里不用过滤用户
                    Tag tag = this.getOne(new LambdaQueryWrapper<Tag>().eq(Tag::getTagName, tagName));
                    if (tag == null) {
                        saveOrUpdateTagList.add(new Tag(tagName));
                    } else {
                        saveOrUpdateTagList.add(tag);
                    }
                }
            }
        }
        List<Long> tagIds = null;
        if (saveOrUpdateTagList != null && saveOrUpdateTagList.size() > 0) {
            this.saveOrUpdateBatch(saveOrUpdateTagList);
            // 更新文章与标签关联关系
            tagIds = saveOrUpdateTagList.stream().map(Tag::getId).collect(Collectors.toList());
            articleTagRelService.updateArticleTagRel(articleId, tagIds);
            // 更新标签文章数量
            incrTagNum(tagIds);
        }
        return tagIds;
    }

    @Override
    public void updateTagHot(Long tagId) {
        UpdateWrapper<Tag> wrapper = new UpdateWrapper<>();
        wrapper.setSql(true, "hot=hot+1");
        wrapper.lambda().eq(Tag::getId, tagId);
        this.update(wrapper);
    }

    /**
     * 点击次数最高热度最后，其次是文章数量引用最多
     *
     * @param size hot tag num
     * @return
     */
    @Override
    public List<JSONObject> getHotTag(Integer size) {
        Page<Tag> page = this.page(new Page<>(1, size == null ? 100 : size), new LambdaQueryWrapper<Tag>()
                .orderByDesc(Tag::getHot, Tag::getNum));
        List<JSONObject> hostTags = page.getRecords().stream().map(tag -> {
            JSONObject json = new JSONObject();
            json.put("value", tag.getId());
            json.put("label", tag.getTagName());
            json.put("num", tag.getNum());
            json.put("hot", tag.getHot());
            return json;
        }).collect(Collectors.toList());
        return hostTags;
    }

    @Transactional
    @Override
    public void removeArticleTagRel(List<Article> articleList) {
        if (CollectionUtil.isEmpty(articleList)) {
            return;
        }
        List<Long> deleteTagIds = new ArrayList<>();
        for (Article article : articleList) {
            String[] tagIdStr = article.getTags().split(",");
            if (tagIdStr == null || tagIdStr.length == 0) {
                continue;
            }
            // String数组转Long数组
            Long[] tagIds = (Long[]) ConvertUtils.convert(tagIdStr, Long.class);
            // 删除文章标签关系
            articleTagRelService.remove(new LambdaQueryWrapper<ArticleTagRel>()
                    .eq(ArticleTagRel::getArticleId, article.getId()).in(ArticleTagRel::getTagId, tagIds));
            deleteTagIds.addAll(Arrays.asList(tagIds));
        }
        // 减掉标签关联的文章数量
        decrTagNum(deleteTagIds);
    }

    @Override
    public void incrTagNum(List<Long> tagIds) {
        this.update(new LambdaUpdateWrapper<Tag>().in(Tag::getId, tagIds).setSql("num=num+1"));
    }

    @Override
    public void decrTagNum(List<Long> tagIds) {
        this.update(new LambdaUpdateWrapper<Tag>().in(Tag::getId, tagIds).gt(Tag::getNum, 0).setSql("num=num-1"));
    }

    @Override
    public String getTagNames(String tagIdStr) {
        if (StrUtil.isBlank(tagIdStr)) {
            return null;
        }
        String[] split = tagIdStr.split(",|，|\\|");
        Long[] tagIds = (Long[]) ConvertUtils.convert(split, Long.class);
        List<Tag> tagList = this.list(new LambdaQueryWrapper<Tag>().in(Tag::getId, tagIds));
        List<String> tagNames = tagList.stream().map(Tag::getTagName).collect(Collectors.toList());
        return CollectionUtil.join(tagNames, ",");
    }

}
