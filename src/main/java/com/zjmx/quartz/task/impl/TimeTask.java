package com.zjmx.quartz.task.impl;

import com.zjmx.quartz.task.Task;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @author 26340
 */
@Component("timeTask")
public class TimeTask implements Task {
    @Override
    public void doWork(String params) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = format.format(new Date());
        System.out.println("Time开始工作，当前时间为:"+dateString+"参数："+params);
    }
}
