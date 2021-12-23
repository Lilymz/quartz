package com.zjmx.quartz.config;

import com.zjmx.quartz.JobModel;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitSchedule {
    @Autowired
    private Scheduler scheduler;
    @PostConstruct
    void init() throws SchedulerException {
        JobModel jobModel = new JobModel();
        jobModel.setId("1");
        jobModel.setParams("2021");
        jobModel.setBeanName("timeTask");
        jobModel.setState("0");
        JobDetail jobDetail = JobBuilder.newJob(StandardJob.class).withIdentity("TASK_" + jobModel.getId(),
                "job_group1").build();
        //表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                .withMisfireHandlingInstructionDoNothing();
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("TASK_" + jobModel.getId()).withSchedule(scheduleBuilder).build();

        //放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put("quartz-job", jobModel);

        scheduler.scheduleJob(jobDetail, trigger);

        //暂停任务
        if(jobModel.getState().equals("1")){
            pauseJob(scheduler, Long.parseLong(jobModel.getId()));
        }
    }
    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException("暂停定时任务失败", e);
        }
    }
    /**
     * 获取jobKey
     */
    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey("TASK_" + jobId);
    }
}
