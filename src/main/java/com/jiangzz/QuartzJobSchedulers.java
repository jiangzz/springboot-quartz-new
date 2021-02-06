package com.jiangzz;

import com.jiangzz.job.CronTriggerListener;
import com.jiangzz.job.QuartzJob;
import org.quartz.*;
import org.quartz.impl.matchers.KeyMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobSchedulers {


    public void scheduleJob(Scheduler scheduler) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class) .withIdentity("job1", "group1").build();
        JobKey jobKey = JobKey.jobKey("job1", "group1");
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
        scheduler.getListenerManager().addTriggerListener(new CronTriggerListener(), KeyMatcher.keyEquals(triggerKey));

        if(scheduler.checkExists(jobKey)){
            scheduler.resumeJob(jobKey);
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
