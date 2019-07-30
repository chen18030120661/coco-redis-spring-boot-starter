package com.springboot.cxy.redis.module.quartzjob;


import com.springboot.cxy.redis.module.quartzjob.entity.QuartzInfoEntity;
import com.springboot.cxy.redis.module.quartzjob.entity.QuartzInfoModel;
import com.springboot.cxy.redis.module.quartzjob.service.QuartzInfoService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import tool.util.BeanUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuartzListener implements ServletContextListener{

	private static Logger logger=Logger.getLogger(QuartzListener.class);
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		logger.info("【启动所有任务】开始...");
		try {
			QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
		
			// 查询启用状态的定时任务信息
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("state", QuartzInfoModel.STATE_ENABLE);
			List<QuartzInfoEntity> list = quartzInfoService.list(paramMap);
			QuartzManager quartzManager = new QuartzManager();
			// 循环添加任务
			for (QuartzInfoEntity quartzInfo : list) {
				String clName = quartzInfo.getClassName();
				Job cl = (Job) Class.forName(clName).newInstance();
				quartzManager.addJob(quartzInfo.getCode(),QuartzManager.JOB_GROUP_NAME, cl.getClass(),quartzInfo.getCycle());
			}
			
			// 启动所有定时任务			
			quartzManager.start();

		} catch (Exception e) {
			logger.error("启动定时任务异常--->" + e.getMessage(), e);
		}
		logger.info("【启动所有任务】结束...");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
