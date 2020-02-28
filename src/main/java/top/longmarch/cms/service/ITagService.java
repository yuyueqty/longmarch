package top.longmarch.cms.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.cms.entity.Article;
import top.longmarch.cms.entity.Tag;

import java.util.List;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-02-27
 */
public interface ITagService extends IService<Tag> {

    void saveTag(Tag tag);

    List<Long> updateArticleTag(Long articleId, String tagNames);

    void updateTagHot(Long tagId);

    List<JSONObject> getHotTag(Integer size);

    void removeArticleTagRel(List<Article> articleList);

    void incrTagNum(List<Long> tagIds);

    void decrTagNum(List<Long> tagIds);

    String getTagNames(String tagIds);

}
