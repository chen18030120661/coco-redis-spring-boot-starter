package com.springboot.cxy.redis.module.common.util.global.service.impl;


import com.springboot.cxy.redis.module.common.util.global.entity.SysConfig;
import com.springboot.cxy.redis.module.common.util.global.mapper.SysConfigMapper;
import com.springboot.cxy.redis.module.common.util.global.service.SysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统参数ServiceImpl
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
 */
@Service(value = "sysConfigService")
public class SysConfigServiceImpl implements SysConfigService {

    private static final Logger log = LoggerFactory.getLogger(SysConfigServiceImpl.class);

    @Resource
    private SysConfigMapper sysConfigMapper;

	@Override
	public List<SysConfig> findAll() {
		return sysConfigMapper.findAll();
	}


}