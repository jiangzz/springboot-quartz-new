package com.jiangzz;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDefineComandRunner implements CommandLineRunner {
    @Autowired
    QuartzJobSchedulers quartzJobSchedulers;
    @Autowired
    private Scheduler scheduler;
    @Override
    public void run(String... args) throws Exception {
        quartzJobSchedulers.scheduleJob(scheduler);
    }
}
