package com.jiangzz.job;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.TriggerListener;

public class CronTriggerListener implements TriggerListener {
    @Override
    public String getName() {
        return "CronTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext jobExecutionContext) {

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext jobExecutionContext) {
        System.out.println("voteJobFired");
        return false;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {

    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext jobExecutionContext,
                                Trigger.CompletedExecutionInstruction completedExecutionInstruction) {
            try{
                jobExecutionContext.getScheduler().pauseTrigger(trigger.getKey());
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
