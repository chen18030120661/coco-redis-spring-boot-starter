package com.springboot.cxy.redis.module.quartz.service;

import com.github.pagehelper.Page;
import com.springboot.cxy.redis.module.quartz.entity.SysLogEntity;

import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 切面日志Service
 */
public interface SysLogService{

	/**
	 *  获取操作日志列表
	 * @return
	 */
	Page<SysLogEntity> sysLogList(Map<String, Object> params, int current, int pageSize);

	int insert(SysLogEntity log);
}
