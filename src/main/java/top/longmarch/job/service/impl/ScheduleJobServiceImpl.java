package top.longmarch.job.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.longmarch.job.dao.ScheduleJobDao;
import top.longmarch.job.entity.ScheduleJob;
import top.longmarch.job.service.IScheduleJobService;
import top.longmarch.job.utils.ScheduleUtils;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 定时任务 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2019-04-07
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobDao, ScheduleJob>
        implements IScheduleJobService {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private ScheduleJobDao scheduleJobDao;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<ScheduleJob> scheduleJobList = scheduleJobDao.selectAll();
        for (ScheduleJob scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getId());
            // 如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            } else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
        }
    }

    @Override
    public Page<ScheduleJob> queryPage(Map<String, Object> params) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(ScheduleJob scheduleJob) {
        scheduleJob.setId(null);
        this.save(scheduleJob);
        ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ScheduleJob scheduleJob) {
        updateById(scheduleJob);
        ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.deleteScheduleJob(scheduler, jobId);
        }
        super.removeByIds(Arrays.asList(jobIds));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateBatch(Long[] jobIds, Boolean status) {
        Map<String, Object> map = new HashMap<>();
        map.put("list", Arrays.asList(jobIds));
        map.put("status", status);
        return scheduleJobDao.updateBatch(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.run(scheduler, this.getById(jobId));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void pause(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.pauseJob(scheduler, jobId);
        }
        this.updateBatch(jobIds, false);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resume(Long[] jobIds) {
        for (Long jobId : jobIds) {
            ScheduleUtils.resumeJob(scheduler, jobId);
        }
        this.updateBatch(jobIds, true);
    }

}
