package com.cxy.spring.boot.module.quartz.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/30
 * @desc : 定时任务详情实体
 */
@Data
public class QuartzInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    private Long id;

    /**
     * 定时任务名称
     */
    private String name;

    /**
     * 定时任务标识
     */
    private String code;

    /**
     * 定时任务执行周期
     */
    private String cycle;

    /**
     * 定时任务执行类
     */
    private String className;

    /**
     * 成功执行次数
     */
    private Integer succeed;

    /**
     * 失败执行次数
     */
    private Integer fail;

    /**
     * 是否启用 10-启用 20-禁用
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

}