package com.cxy.spring.boot.module.common.controller;

import com.cxy.spring.boot.module.common.service.SysConfigService;
import com.cxy.spring.boot.module.common.util.Global;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数Controller
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
 */
@Api(description = "系统配置Api")
@RestController
public class SysController {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 重加载系统配置数据
     *
     * @throws Exception
     */
    @ApiOperation(value = "获取系统配置表全部信息", notes = "获取全部信息，并刷新系统中的缓存数据", httpMethod = "GET")
    @RequestMapping("/api/system/config/reload")
    public void reload(HttpServletResponse response) {
        // 重加载系统配置数据
        sysConfigService.initSysConfig();
    }

    /**
     * 获取系统版本配置信息
     *
     */
    @RequestMapping(value = "/api/app/findSysVersionInfo.htm", method = RequestMethod.POST)
    public Map<String, Object> findSysVersionInfo() {
        Map<String, Object> restMap = new HashMap<>();
        restMap.put("forceUpdate", Global.getValue("force_update"));  //是否强制更新：10--否，20--是
        restMap.put("updateContent", Global.getValue("update_content"));  //手机app最新版本更新内容
        restMap.put("androidVersion", Global.getValue("android_version"));  //最新android版本
        restMap.put("androidAddress", Global.getValue("android_address"));   //最新android版本下载地址
        restMap.put("iosVersion", Global.getValue("ios_version"));  //最新ios版本
        restMap.put("iosAddress", Global.getValue("ios_address"));   //最新ios版本下载地址
        return restMap;
    }

}
