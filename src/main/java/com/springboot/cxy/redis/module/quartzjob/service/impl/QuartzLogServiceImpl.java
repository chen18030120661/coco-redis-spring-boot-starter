package com.springboot.cxy.redis.module.quartzjob.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.cxy.redis.module.quartzjob.entity.QuartzLogEntity;
import com.springboot.cxy.redis.module.quartzjob.entity.QuartzLogModel;
import com.springboot.cxy.redis.module.quartzjob.mapper.QuartzLogMapper;
import com.springboot.cxy.redis.module.quartzjob.service.QuartzLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 定时任务记录ServiceImpl
 */
@SuppressWarnings("unused")
@Service("quartzLogService")
public class QuartzLogServiceImpl implements QuartzLogService {
	
    
	private static final Logger logger = LoggerFactory.getLogger(QuartzLogServiceImpl.class);
   
    @Resource
    private QuartzLogMapper quartzLogMapper;

	@Override
	public int save(QuartzLogEntity ql) {
		return 0;
	}

	@Override
	public Page<QuartzLogModel> page(Map<String, Object> searchMap,
									 int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<QuartzLogModel> list = quartzLogMapper.page(searchMap);
		return (Page<QuartzLogModel>)list;
	}
	
}