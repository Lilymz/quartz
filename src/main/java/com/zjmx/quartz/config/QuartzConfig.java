package com.zjmx.quartz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.Properties;

@Configuration
public class QuartzConfig {
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factory=new SchedulerFactoryBean();
//        factory.setDataSource(dataSource);
        Properties properties = new Properties();
        factory.setSchedulerName("ConfigurationScheduler");
        properties.put("org.quartz.scheduler.instanceName","ConfigurationQuartzScheduler");
        properties.put("org.quartz.sche duler.instanceId","AUTO");
        properties.put("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
        properties.put("org.quartz.threadPool.threadCount","20");
        properties.put("org.quartz.threadPool.threadPriority","5");
        properties.put("org.quartz.threadPool.threadNamePrefix","zjmx-worker");
        properties.put("org.quartz.jobStore.class","org.quartz.simpl.RAMJobStore");
        properties.put("org.quartz.jobStore.misfireThreshold","120");
        properties.put("org.quartz.triggerListener.NAME.class","com.zjmx.quartz.listener.MyListener");
//        properties.put("org.quartz.triggerListener.NAME.propName","MyListener");
        //延时启动
        factory.setStartupDelay(5);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);
        factory.setQuartzProperties(properties);
        return factory;
    }
}
