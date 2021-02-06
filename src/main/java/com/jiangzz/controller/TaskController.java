package com.jiangzz.controller;


import com.jiangzz.entity.JobInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class TaskController {
    @Autowired
    private Scheduler scheduler;

    @GetMapping("/jobs")
    //方法描述
    @ApiOperation(notes = "查看所有的job", value = "showJobs")
    public List<JobInfo> showJobs() throws SchedulerException {
        List<JobInfo> jobInfos=new ArrayList<>();
        List<JobExecutionContext> currentlyExecutingJobs = scheduler.getCurrentlyExecutingJobs();
        for (JobExecutionContext currentlyExecutingJob : currentlyExecutingJobs) {
            JobKey key = currentlyExecutingJob.getJobDetail().getKey();
            jobInfos.add(new JobInfo(key.getName(),key.getGroup()));
        }
        return jobInfos;
    }
    @GetMapping("/trigger")
    //方法描述
    @ApiOperation(notes = "查看所有的触发器", value = "showTrigger")
    public List<JobInfo> showTriggers() throws SchedulerException {
        List<JobInfo> jobInfos=new ArrayList<>();
        Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.anyGroup());
        for (TriggerKey triggerKey : triggerKeys) {
            jobInfos.add(new JobInfo(triggerKey.getName(),triggerKey.getGroup()));
        }
        return jobInfos;
    }
    @PutMapping("/trigger")
    //方法描述
    @ApiOperation(notes = "暂停触发器", value = "pauseTrigger")
    public void pauseTrigger(@ApiParam(name = "group",value="组") @RequestParam(name = "group") String  group,
                                       @ApiParam(name = "key",value="键") @RequestParam(name = "key")  String key) throws SchedulerException {

        scheduler.pauseTrigger(TriggerKey.triggerKey(group,key));

    }
    @GetMapping("/pausedTrigger")
    //方法描述
    @ApiOperation(notes = "查询暂停任务", value = "getPauseTrigger")
    public List<JobInfo> getPauseTrigger() throws SchedulerException {
        List<JobInfo> jobInfos=new ArrayList<>();
        Set<String> groups = scheduler.getPausedTriggerGroups();
        for (String g : groups) {
            Set<TriggerKey> triggerKeys = scheduler.getTriggerKeys(GroupMatcher.groupContains(g));
            for (TriggerKey triggerKey : triggerKeys) {
                jobInfos.add(new JobInfo(triggerKey.getName(),triggerKey.getGroup()));
            }
        }
        return jobInfos;
    }
}
