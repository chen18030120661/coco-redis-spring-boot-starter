package com.springboot.cxy.redis.module.quartzjob.mapper;

import com.springboot.cxy.redis.module.quartzjob.entity.SysLogEntity;

import java.util.List;
import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 系统日志mapper
 */
public interface SysLogMapper{

	List<SysLogEntity> findPage(Map<String, Object> params);

}
