package com.cxy.spring.boot.module.shardtable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户通讯录黑名单统计事件.
 *
 * @author : xy.chen
 * @time : 2019/7/29
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserContactsCountEvent {

    private Long userId;

    private String tableName;
}
