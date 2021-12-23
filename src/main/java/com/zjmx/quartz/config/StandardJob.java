package com.zjmx.quartz.config;

import com.zjmx.quartz.JobModel;
import com.zjmx.quartz.util.SpringContextUtil;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.lang.reflect.Method;

public class StandardJob extends QuartzJobBean {

    private static final Logger logger = LoggerFactory.getLogger(StandardJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) {
        JobModel jobModel = (JobModel)context.getMergedJobDataMap().get("quartz-job");
        try {
            Object bean = SpringContextUtil.getBean(jobModel.getBeanName());
            Method method = bean.getClass().getDeclaredMethod("doWork", String.class);
            method.invoke(bean,jobModel.getParams());
        }catch (Exception e){
            logger.error(jobModel.getBeanName()+"---job执行出错:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
