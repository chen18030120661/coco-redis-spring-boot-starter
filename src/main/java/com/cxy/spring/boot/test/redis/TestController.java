package com.cxy.spring.boot.test.redis;

import com.cxy.spring.boot.module.msgqueue.MsgPublisher;
import com.cxy.spring.boot.module.msgqueue.entity.MsgQueueEntity;
import com.cxy.spring.boot.module.quartz.annotation.AddSysLog;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Resource
    private TestService testService;

    @Resource
    private MsgPublisher msgPublisher;
    @AddSysLog(desc = "test测试")
    @RequestMapping("/api/redis/test")
    public void test() {
//        String test1 = testService.test("测试1号", 10);
//        System.out.println(test1);
//        String test2 = testService.test("测试1号", 10);
//        System.out.println(test2);
//        String test3 = testService.test("测试3号", 30);
//        System.out.println(test3);
//        User user = new User("小明",15);
//        String age = testService.testUser(user);
//        System.out.println(age);
//       List<MsgQueueEntity> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        for (int i = 10; i < 15; i++) {
            MsgQueueEntity msgQueueEntity = new MsgQueueEntity();
            msgQueueEntity.setLoginName("测试"+i+"号");
            msgQueueEntity.setLoginPwd("密码"+i+"号");
            map.put("aaa", msgQueueEntity);
            msgPublisher.push(msgQueueEntity);
//            list.add(msgQueueEntity);
        }
//        int num = testService.insertTestBath(list);


//        System.out.println(num);
    }
}
