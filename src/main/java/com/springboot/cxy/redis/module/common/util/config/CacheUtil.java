package com.springboot.cxy.redis.module.common.util.config;

import com.springboot.cxy.redis.module.common.util.Global;
import com.springboot.cxy.redis.module.common.util.entity.SysConfig;
import com.springboot.cxy.redis.module.common.util.service.SysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tool.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 缓存帮助类
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
 */
public class CacheUtil {

    private static final Logger logger = LoggerFactory.getLogger(CacheUtil.class);

    /**
     * 初始化系统参数配置
     */
    public static void initSysConfig(SysConfigService sysConfigService ) {
        logger.info("初始化系统配置Config...");
        Map<String, Object> configMap = new HashMap<String, Object>();
        List<SysConfig> sysConfigs = sysConfigService.findAll();
        for (SysConfig sysConfig : sysConfigs) {
            if (null != sysConfig && StringUtil.isNotBlank(sysConfig.getCode())) {
                configMap.put(sysConfig.getCode(), sysConfig.getValue());
            }
        }
        Global.configMap = new HashMap<String, Object>();
        Global.configMap.putAll(configMap);
    }

}