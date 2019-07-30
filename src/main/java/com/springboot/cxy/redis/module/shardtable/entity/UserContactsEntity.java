package com.springboot.cxy.redis.module.shardtable.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : xy.chen
 * @time : 2019/7/29
 * @desc : 用户通讯录表实体
 */
 @Data
 public class UserContactsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 用户标识
    */
    private Long userId;

    /**
    * 姓名
    */
    private String name;

    /**
    * 手机号码
    */
    private String phone;

    /**
    * 通话次数
    */
    private Integer totalCount;

    /**
    * 通话总时长
    */
    private Integer sumDuration;

}