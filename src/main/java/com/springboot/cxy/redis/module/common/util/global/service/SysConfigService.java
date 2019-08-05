package com.springboot.cxy.redis.module.common.util.global.service;


import com.springboot.cxy.redis.module.common.util.global.entity.SysConfig;

import java.util.List;

/**
 * 系统参数Service
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
*/
public interface SysConfigService {

   	 /**
   	  * 查询所有配置
   	  * @return
   	  * @throws Exception
   	  */
   	 List<SysConfig> findAll();

}
