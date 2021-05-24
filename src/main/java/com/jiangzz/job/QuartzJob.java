package com.jiangzz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class QuartzJob extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<String> messages = (List<String>) context.getTrigger().getJobDataMap().get("messages");
        /*for (String message : messages) {
            String taskName = context.getJobDetail().getJobDataMap().getString("name");
            log.info("---> Quartz job {}, {},{} <----", new Date(), taskName,messages);
        }*/
        System.out.println("执行job参数");

    }
}
