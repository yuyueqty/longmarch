package top.longmarch.cms.service.impl;

import top.longmarch.cms.entity.Category;
import top.longmarch.cms.dao.CategoryDao;
import top.longmarch.cms.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 文章类目 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements ICategoryService {

}
