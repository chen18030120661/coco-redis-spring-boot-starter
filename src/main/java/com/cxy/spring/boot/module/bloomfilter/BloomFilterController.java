package com.cxy.spring.boot.module.bloomfilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Api(description = "布隆过滤器")
@RestController
@RequestMapping("/api/bloom")
public class BloomFilterController {

    @Resource
    private BloomFilterService bloomFilterService;

    @ApiOperation("添加")
    @PostMapping(value = "/addUuid")
    public boolean addUuid(String uuid) {
        return bloomFilterService.addUuid(uuid);
    }

    @ApiOperation("判断是否存在")
    @PostMapping(value = "/existsUuid")
    public boolean existsUuid(String uuid) {
        return bloomFilterService.containMulit(uuid);
    }

}
