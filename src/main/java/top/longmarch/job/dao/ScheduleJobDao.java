package top.longmarch.job.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.longmarch.job.entity.ScheduleJob;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 Mapper 接口
 * </p>
 *
 * @author YuYue
 * @since 2019-04-07
 */
public interface ScheduleJobDao extends BaseMapper<ScheduleJob> {

    List<ScheduleJob> selectAll();

    /**
     * 批量更新状态
     */
    int updateBatch(Map<String, Object> params);

}
