package com.springboot.cxy.redis.module.quartz.mapper;

import com.springboot.cxy.redis.module.quartz.entity.QuartzLogEntity;
import com.springboot.cxy.redis.module.quartz.entity.QuartzLogModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/30
 * @desc : 定时任务记录mapper
 */
public interface QuartzLogMapper {

	/**
	 * 据quartId查询最后执行时间
	 * @param quartId
	 * @return
	 */
	String findLastTimeByQzInfoId(@Param("quartzId") Long quartId);

	/**
	 * 日志查询
	 * @param searchMap
	 * @return
	 */
	List<QuartzLogModel> page(Map<String, Object> searchMap);

	/**
	 * 保存定时日志
	 * @param quartzLogEntity
	 * @return
	 */
	int save(QuartzLogEntity quartzLogEntity);
}
