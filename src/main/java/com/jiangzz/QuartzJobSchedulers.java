package com.jiangzz;

import com.jiangzz.job.QuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobSchedulers {


    public void scheduleJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class) .withIdentity("job1", "group1").build();
        JobKey jobKey = JobKey.jobKey("job1", "group1");
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");

        if(scheduler.checkExists(jobKey)){
            if (scheduler.checkExists(triggerKey)) {
                Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                if(triggerState.name().equals("PAUSED")){
                    scheduler.resumeTrigger(triggerKey);
                }
            }
        }else{
            // 6的倍数秒执行 也就是 6 12 18 24 30 36 42 ....
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/6 * * * * ?");
            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group1")
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail,cronTrigger);
        }
    }
}
