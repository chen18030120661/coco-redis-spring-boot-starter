package com.springboot.cxy.redis.module.quartzjob.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.cxy.redis.module.quartzjob.entity.SysLogEntity;
import com.springboot.cxy.redis.module.quartzjob.mapper.SysLogMapper;
import com.springboot.cxy.redis.module.quartzjob.service.SysLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 切面日志ServiceImpl
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
	
    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);
   
    @Resource
    private SysLogMapper sysLogMapper;

	@Override
	public Page<SysLogEntity> sysLogList(Map<String, Object> params, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		return (Page<SysLogEntity>)sysLogMapper.findPage(params);
	}

	@Override
	public int insert(SysLogEntity log) {
		return sysLogMapper.save(log);
	}
}