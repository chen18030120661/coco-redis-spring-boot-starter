package com.springboot.cxy.redis.module.quartzjob.service;

import com.github.pagehelper.Page;
import com.springboot.cxy.redis.module.quartzjob.entity.QuartzLogEntity;
import com.springboot.cxy.redis.module.quartzjob.entity.QuartzLogModel;

import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 定时任务记录Service
 */
public interface QuartzLogService{

	/**
	 * 保存日志
	 */
	int save(QuartzLogEntity ql);

	Page<QuartzLogModel> page(Map<String, Object> searchMap, int current,
							  int pageSize);

}
