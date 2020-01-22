package top.longmarch.core.common.bean;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Date;

@Data
public class SearchSuper {

    public static final String STATUS = "status";
    public static final String CREATE_TIME = "create_time";

    public static final long CURRENT_VALUE = 1;
    public static final long SIZE_VALUE = 10;

    private Long current = CURRENT_VALUE;
    private Long size = SIZE_VALUE;
    private Integer status;
    private String fuzzy;
    private Date beginTime;
    private Date endTime;
    private String[] columns;
    private String[] ascs;
    private String[] descs;

    public <T> QueryWrapper<T> lambdaQuery() {
        QueryWrapper wrapper = Wrappers.query();
        wrapper.select(columns);
        wrapper.eq(searchStatus(), STATUS, status);
        wrapper.between(searchTime(), CREATE_TIME, beginTime, endTime);
        return wrapper;
    }

    public <T> Page<T> page() {
        Page<T> page = new Page<>(current, size);
        page.setDesc(descs);
        page.setAsc(ascs);
        return page;
    }

    private boolean searchTime() {
        return beginTime != null && endTime != null;
    }

    private boolean searchStatus() {
        return status != null;
    }
}
