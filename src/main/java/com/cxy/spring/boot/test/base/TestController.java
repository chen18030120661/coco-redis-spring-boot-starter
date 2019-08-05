package com.cxy.spring.boot.test.base;

import com.cxy.spring.boot.module.base.format.Result;
import com.cxy.spring.boot.module.base.format.encrypt.Encrypt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * .
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-08-05
 */
@Api(tags = "测试接口文档")
@RestController
@RequestMapping("test")
public class TestController {

    @ApiOperation("接口")
    @GetMapping("index")
    public String index() {
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("yes", "OK");
        return "index";
    }
    @ApiOperation("接口")
    @GetMapping
    public Result get() {
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("yes", "OK");
        return Result.success(hashMap);
    }

    @ApiOperation("接口")
    @PutMapping
    public Map<String, Object> put() {
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("yes", "OK");
        return null;
    }

    @ApiOperation("POST接口")
    @Encrypt
    @PostMapping
    public List gets(@RequestBody Map<String, String> param) {
        if (Integer.valueOf(param.get("id")) == 1) {
            throw new com.cxy.spring.boot.module.interceptor.core.exception.CommonException(123, "zsd");
        }
        final HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("yes", "OK");
        hashMap.put("id", "11");

        final ArrayList arrayList = new ArrayList();
        arrayList.add(hashMap);
        return arrayList;
    }
}
