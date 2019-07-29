package com.springboot.cxy.redis.module.shardtable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : xy.chen
 * @time : 2019/7/29
 * @desc : 用户统计
 */
@Setter
@Getter
@ToString
public class UserCountEntity {
    private Long id;
    private Long userId;
    private Integer contactsBlackCount;
}
