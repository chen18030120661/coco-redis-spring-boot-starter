package com.springboot.cxy.redis.module.quartz.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 系统日志实体
 */
 @Data
 public class SysLogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 请求方法名
    */
    private String method;

    /**
    * 操作设备ip地址
    */
    private String remoteAddr;

    /**
    * 请求地址uri
    */
    private String requestUri;

    /**
    * 操作人
    */
    private String addUser;

    /**
    * 操作时间
    */
    private Date createTime;

    /**
    * 操作内容
    */
    private String content;

}