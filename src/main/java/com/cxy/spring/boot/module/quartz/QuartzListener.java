package com.cxy.spring.boot.module.quartz;

import com.cxy.spring.boot.module.quartz.entity.QuartzInfoEntity;
import com.cxy.spring.boot.module.quartz.entity.QuartzInfoModel;
import com.cxy.spring.boot.module.quartz.service.QuartzInfoService;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 定时任务初始化调度器
 */
//public class QuartzListener implements ServletContextListener{
@Component
public class QuartzListener implements CommandLineRunner {

	private static Logger logger=Logger.getLogger(QuartzListener.class);

	@Resource
	private QuartzInfoService quartzInfoService;
	

//	@Override
//	public void contextInitialized(ServletContextEvent sce) {
//	@PostConstruct
//    public void initScheduler() {
//		logger.info("【启动所有任务】开始...");
//		try {
//			QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
//
//			// 查询启用状态的定时任务信息
//			Map<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("state", QuartzInfoModel.STATE_ENABLE);
//			List<QuartzInfoEntity> list = quartzInfoService.list(paramMap);
//			QuartzManager quartzManager = new QuartzManager();
//			// 循环添加任务
//			for (QuartzInfoEntity quartzInfo : list) {
//				String clName = quartzInfo.getClassName();
//				Job cl = (Job) Class.forName(clName).newInstance();
//				quartzManager.addJob(quartzInfo.getCode(),QuartzManager.JOB_GROUP_NAME, cl.getClass(),quartzInfo.getCycle());
//			}
//
//			// 启动所有定时任务
//			quartzManager.start();
//
//		} catch (Exception e) {
//			logger.error("启动定时任务异常--->" + e.getMessage(), e);
//		}
//		logger.info("【启动所有任务】结束...");
//	}

//	@Override
//	public void contextDestroyed(ServletContextEvent sce) {
//
//	}
	@Override
	public void run(String... args) throws Exception {
		logger.info("【启动所有任务】开始...");
		try {
//			QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");

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
}
