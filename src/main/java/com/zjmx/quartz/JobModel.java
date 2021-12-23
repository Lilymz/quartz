package com.zjmx.quartz;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobModel {
    private String id ;
    private String params;
    private String beanName;
    private String state;
}
