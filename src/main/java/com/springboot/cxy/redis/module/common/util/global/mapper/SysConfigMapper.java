package com.springboot.cxy.redis.module.common.util.global.mapper;


import com.springboot.cxy.redis.module.common.util.global.entity.SysConfig;

import java.util.List;

/**
 * 系统参数Dao
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
*/
public interface SysConfigMapper {

    /**
     * 查询所有系统配置
     * @return
     */
    List<SysConfig> findAll();
    
}
