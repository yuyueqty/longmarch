package top.longmarch.job.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.LmUtils;
import top.longmarch.job.entity.ScheduleJob;
import top.longmarch.job.service.IScheduleJobLogService;
import top.longmarch.job.service.IScheduleJobService;

import java.util.Map;
import java.util.Objects;

/**
 * 定时任务
 */
@Api(value = "定时任务", tags = "定时任务接口")
@RestController
@RequestMapping("/job/schedule")
public class ScheduleJobController {

    @Autowired
    private IScheduleJobService scheduleJobService;
    @Autowired
    private IScheduleJobLogService scheduleJobLogService;

    @ApiOperation(value = "任务列表")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        params = PageFactory.buildMap(params);
        IPage<ScheduleJob> page = PageFactory.getInstance(params);
        Object status = params.get(Constant.STATUS);
        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        Wrapper<ScheduleJob> wrapper = new QueryWrapper<ScheduleJob>().lambda()
                .eq(LmUtils.isNotBlank(status), ScheduleJob::getStatus, status)
                .and(LmUtils.isNotBlank(fuzzySearch), p ->
                        p.like(ScheduleJob::getBeanName, fuzzySearch)
                                .or().like(ScheduleJob::getMethodName, fuzzySearch)
                                .or().like(ScheduleJob::getRemark, fuzzySearch))
                .orderByDesc(ScheduleJob::getId);
        return Result.ok().add(scheduleJobService.page(page, wrapper));
    }

    @ApiOperation(value = "查看任务")
    @RequiresPermissions("job:schedule:show")
    @GetMapping("/show/{jobId}")
    public Result show(@PathVariable("jobId") Long jobId) {
        return Result.ok().add(scheduleJobService.getById(jobId));
    }

    @Log
    @ApiOperation(value = "创建任务")
    @RequiresPermissions("job:schedule:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.insert(scheduleJob);
        return Result.ok().add(scheduleJob);
    }

    @Log
    @ApiOperation(value = "修改任务")
    @RequiresPermissions("job:schedule:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody ScheduleJob scheduleJob) {
        scheduleJobService.updateById(scheduleJob);
        return Result.ok().add(scheduleJob);
    }

    @Log
    @ApiOperation(value = "删除任务")
    @RequiresPermissions("job:schedule:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] jobIds) {
        scheduleJobService.deleteBatch(jobIds);
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "立即任务")
    @RequiresPermissions("job:schedule:run")
    @PostMapping("/run")
    public Result run(@RequestBody Long[] jobIds) {
        scheduleJobService.run(jobIds);
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "暂停任务")
    @RequiresPermissions("job:schedule:pause")
    @PostMapping("/pause")
    public Result pause(@RequestBody Long[] jobIds) {
        scheduleJobService.pause(jobIds);
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "恢复任务")
    @RequiresPermissions("job:schedule:resume")
    @PostMapping("/resume")
    public Result resume(@RequestBody Long[] jobIds) {
        scheduleJobService.resume(jobIds);
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "重置任务")
    @RequiresPermissions("job:schedule:reset")
    @PostMapping("/reset")
    public Result reset(@RequestBody Long[] jobIds) {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setCount(0);
        scheduleJobService.update(scheduleJob, new LambdaUpdateWrapper<ScheduleJob>().in(ScheduleJob::getId, jobIds));
        return Result.ok();
    }

}
