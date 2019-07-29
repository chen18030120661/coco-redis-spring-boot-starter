package com.springboot.cxy.redis.mapper;

import com.springboot.cxy.redis.module.shardtable.UserContactsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author : xy.chen
 * @time : 2019/7/29
 * @desc : Mapper
 */
public interface UserContactsMapper {

    /**
     * 根据表名查询表数量
     * @param tableName
     * @return
     */
    int countTable(String tableName);

    /**
     * 根据表名创建表
     * @param tableName
     */
    void createTable(@Param("tableName") String tableName);

    /**
     * (分表)新增
     * @param tableName
     * @param userContacts
     * @return
     */
    int saveShard(@Param("tableName") String tableName, @Param("item") UserContactsEntity userContacts);

    /**
     * 根据参数(分表)查询
     * @param tableName
     * @param params
     * @return
     */
    List<UserContactsEntity> listShardSelective(
            @Param("tableName") String tableName,
            @Param("params") Map<String, Object> params);
}
