package com.cxy.spring.boot.module.quartz.service;

import java.util.List;
import java.util.Map;


import com.cxy.spring.boot.module.quartz.entity.QuartzInfoEntity;
import com.cxy.spring.boot.module.quartz.entity.QuartzInfoModel;
import com.github.pagehelper.Page;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 定时任务详情Service
 */
public interface QuartzInfoService{


	/**
	 * 保存定时任务数据
	 * @param qi
	 */
	boolean save(QuartzInfoEntity qi);

	/**
	 * 修改定时任务
	 * @param search
	 * @return
	 */
	boolean update(Map<String, Object> search);

	/**
	 * 查询所有任务
	 * @param result
	 * @return
	 */
	List<QuartzInfoEntity> list(Map<String, Object> result);

	/**
	 * 定时任务分页查询
	 * @param searchMap
	 * @param current
	 * @param pageSize
	 * @return
	 */
	Page<QuartzInfoModel> page(Map<String, Object> searchMap, int current,
                               int pageSize);
	
	/**
	 * 据任务标识查询任务
	 * @param code
	 * @return
	 */
	QuartzInfoEntity findByCode(String code);
	
	/**
	 * 据条件查询定时任务详情
	 * @param paramMap
	 * @return
	 */
	QuartzInfoEntity findSelective(Map<String, Object> paramMap);

	/**
	 * 获取任务名
	 * @param searchMap
	 * @return
	 */
	List<QuartzInfoModel> quartzInfoList(Map<String, Object> searchMap);

	QuartzInfoEntity getById(Long id);
}
