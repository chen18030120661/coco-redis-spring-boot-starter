package com.springboot.cxy.redis.serviceTest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    private TestService testService;
    @RequestMapping("/api/redis/test")
    public void test() {
        String test1 = testService.test("测试1号", 10);
        System.out.println(test1);
        String test2 = testService.test("测试1号", 10);
        System.out.println(test2);
        String test3 = testService.test("测试3号", 30);
        System.out.println(test3);
        User user = new User("小明",15);
        String age = testService.testUser(user);
        System.out.println(age);
    }
}
