package com.springboot.cxy.redis.module.shardtable.mapper;

import com.springboot.cxy.redis.module.shardtable.entity.UserCountEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 用户统计持久化.
 *
 * @author xy.chen
 * @date 2019/7/29
 */
public interface UserCountMapper {

    /**
     * 通讯录黑名单统计
     * @auth xy.chen
     * @date 2019/7/29
     */
    int countContactsBlack(@Param("userId") long userId, @Param("tableName") String tableName);

    /**
     * 根据用户ID获取统计信息
     * @auth xy.chen
     * @date 2019/7/29
     */
    UserCountEntity findByUserId(Long userId);

    /**
     * 修改用户统计数据
     * @auth xy.chen
     * @date 2019/7/29
     */
    void update(UserCountEntity userCount);

    /**
     * 新增用户统计数据
     * @auth xy.chen
     * @date 2019/7/29
     */
    void insert(UserCountEntity userCount);
}
