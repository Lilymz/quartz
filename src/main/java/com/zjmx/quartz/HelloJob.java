package com.zjmx.quartz;


import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.util.Date;

public class HelloJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        System.out.println("作业"+jobExecutionContext.getFireInstanceId()+"："+new Date());
    }
}
