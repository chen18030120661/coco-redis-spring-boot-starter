package com.cxy.spring.boot.module.quartz.service;

import com.cxy.spring.boot.module.quartz.entity.QuartzLogEntity;
import com.cxy.spring.boot.module.quartz.entity.QuartzLogModel;
import com.github.pagehelper.Page;


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
