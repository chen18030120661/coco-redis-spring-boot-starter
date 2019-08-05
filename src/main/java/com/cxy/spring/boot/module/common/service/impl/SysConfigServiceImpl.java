package com.cxy.spring.boot.module.common.service.impl;


import com.cxy.spring.boot.module.common.entity.SysConfig;
import com.cxy.spring.boot.module.common.mapper.SysConfigMapper;
import com.cxy.spring.boot.module.common.service.SysConfigService;
import com.cxy.spring.boot.module.common.util.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统参数ServiceImpl
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
 */
@Service(value = "sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {

    private static final Logger logger = LoggerFactory.getLogger(SysConfigServiceImpl.class);

    @Resource
    private SysConfigMapper sysConfigMapper;

	@Override
	public List<SysConfig> findAll() {
		return sysConfigMapper.findAll();
	}

	@Override
	public void initSysConfig() {
		logger.info("初始化系统配置Config...");
		Map<String, Object> configMap = new HashMap<String, Object>();
		List<SysConfig> sysConfigs = sysConfigMapper.findAll();
		for (SysConfig sysConfig : sysConfigs) {
			if (null != sysConfig && StringUtil.isNotBlank(sysConfig.getCode())) {
				configMap.put(sysConfig.getCode(), sysConfig.getValue());
			}
		}
		Global.configMap = new HashMap<String, Object>();
		Global.configMap.putAll(configMap);
	}


}