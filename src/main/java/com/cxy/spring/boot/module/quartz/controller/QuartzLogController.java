package com.cxy.spring.boot.module.quartz.controller;

import com.alibaba.fastjson.JSON;
import com.cxy.spring.boot.module.quartz.entity.QuartzInfoModel;
import com.cxy.spring.boot.module.quartz.entity.QuartzLogModel;
import com.cxy.spring.boot.module.quartz.service.QuartzInfoService;
import com.cxy.spring.boot.module.quartz.service.QuartzLogService;
import com.github.pagehelper.Page;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author : xy.chen
 * @time : 2019/7/30
 * @desc : 定时任务记录Controller
 */
@Scope("prototype")
@Controller
public class QuartzLogController{

	@Resource
	private QuartzLogService quartzLogService;

	@Resource
	private QuartzInfoService quartzInfoService;

	/**
	 * 定时日志列表
	 * @param search
	 * @param current
	 * @param pageSize
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/modules/quartzLog/page.htm")
	public void quartzLog(
			HttpServletResponse response,
			@RequestParam(value="search") String search,
			@RequestParam(value = "current") int current,
			@RequestParam(value = "pageSize") int pageSize) throws Exception {
		Map<String,Object> searchMap = JSON.parseObject(search, Map.class);
		Page<QuartzLogModel> page = quartzLogService.page(searchMap,current, pageSize);
//		Map<String,Object> result = new HashMap<String,Object>();
//		result.put(Constant.RESPONSE_DATA, page);
//		result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
//		result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//		result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
//		ServletUtils.writeToResponse(response,result);
	}

	 /**
	  * 获取任务名
	  * @param request
	  * @param response
	  * @param search
	  * @throws Exception
	  */
	 @SuppressWarnings("unchecked")
	 @RequestMapping(value = "/modules/quartz/taskName.htm")
	 public void page(
			 HttpServletRequest request, HttpServletResponse response,
			 @RequestParam(value="search") String search) throws Exception {
		 Map<String, Object> searchMap = new HashMap<String, Object>();
		 if (!StringUtils.isEmpty(search)) {
			 searchMap = JSON.parseObject(search, Map.class);
		 }
		 List<QuartzInfoModel> list = quartzInfoService.quartzInfoList(searchMap);
//		 Map<String,Object> result = new HashMap<String,Object>();
//		 result.put(Constant.RESPONSE_DATA, list);
//		 result.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
//		 result.put(Constant.RESPONSE_CODE_MSG, "查询成功");
//		 ServletUtils.writeToResponse(response,result);
	 }
}
