package top.longmarch.cms.dao;

import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.longmarch.cms.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文章 Mapper 接口
 * </p>
 *
 * @author YuYue
 * @since 2020-01-30
 */
public interface ArticleDao extends BaseMapper<Article> {

    IPage<Article> search(Page page, Map<String, Object> params);

    @SqlParser(filter = true)
    void batchPublishArticles();

}
