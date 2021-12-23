# quartz-调度
- ## configuration
-  [x] 注意：需要配置线程池类是properties填写得是全限定名
-  [X] 无数据源下得quartz配置,无定制集群
```java
   @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean factory=new SchedulerFactoryBean();
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
        //延时启动
        factory.setStartupDelay(30);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        //可选，QuartzScheduler 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        //设置自动启动，默认为true
        factory.setAutoStartup(true);
        factory.setQuartzProperties(properties);
        return factory;
    }
```