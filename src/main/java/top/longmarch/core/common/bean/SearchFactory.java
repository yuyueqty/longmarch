package top.longmarch.core.common.bean;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public class SearchFactory {

    private static SearchSuper searchSuper = new SearchSuper();

    private SearchFactory() {
    }

    public static SearchSuper getSearchSuper(SearchSuper inputSearchSuper) {
        if (inputSearchSuper != null) {
            searchSuper = inputSearchSuper;
        }
        return searchSuper;
    }

    public static <T> QueryWrapper<T> lambdaQuery(SearchSuper searchSuper) {
        return getSearchSuper(searchSuper).lambdaQuery();
    }

    public static <T> Page<T> page(SearchSuper searchSuper) {
        return getSearchSuper(searchSuper).page();
    }

}
