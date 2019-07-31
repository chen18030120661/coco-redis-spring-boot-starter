package com.springboot.cxy.redis.module.quartz.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/30
 * @desc : 定时任务记录实体
 */
@Data
public class QuartzLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 定时任务id
     */
    private Long quartzId;

    /**
     * 启动时间
     */
    private Date startTime;

    /**
     * 任务用时
     */
    private long time;

    /**
     * 执行是否成功 10-成功 20-失败
     */
    private String result;

    /**
     * 备注信息
     */
    private String remark;

}
