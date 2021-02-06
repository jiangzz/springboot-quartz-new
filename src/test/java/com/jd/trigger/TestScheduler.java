package com.jd.trigger;

import com.jiangzz.BridgeCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@SpringBootTest(classes ={BridgeCenterApplication.class} )
@RunWith(SpringJUnit4ClassRunner.class)
public class TestScheduler {
    @Autowired
    private Scheduler scheduler;
    @Test
    public void pasuseTrigger() throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
        scheduler.pauseTrigger(triggerKey);
    }
    @Test
    public void getTriggerState() throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
        Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
        System.out.println(triggerState.name());
    }
    @Test
    public void checkTriggerExits() throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey("trigger1", "group1");
        boolean checkExists = scheduler.checkExists(triggerKey);
        System.out.println(checkExists);
    }
    @Test
    public void checkJobExits() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("job1", "group1");
        boolean checkExists = scheduler.checkExists(jobKey);
        System.out.println(checkExists);
    }
    @Test
    public void pauseJob() throws SchedulerException {
        JobKey jobKey = JobKey.jobKey("job1", "group1");
        scheduler.pauseJob(jobKey);
    }

}
