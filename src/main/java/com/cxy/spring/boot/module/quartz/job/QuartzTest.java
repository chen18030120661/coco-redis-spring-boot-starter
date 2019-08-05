package com.cxy.spring.boot.module.quartz.job;

import com.cxy.spring.boot.module.quartz.entity.QuartzInfoEntity;
import com.cxy.spring.boot.module.quartz.entity.QuartzLogEntity;
import com.cxy.spring.boot.module.quartz.service.QuartzInfoService;
import com.cxy.spring.boot.module.quartz.service.QuartzLogService;
import com.cxy.spring.boot.module.quartz.util.RedisLock;
import com.cxy.spring.boot.module.quartz.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import tool.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Lazy(value = false)
public class QuartzTest implements Job {

//    @Resource
//    private QuartzInfoService  quartzInfoService;
//
//    @Resource
//    private QuartzLogService quartzLogService;
//
//    @Resource
//    private RedisTemplate redisTemplate;

    /**
     * 定时任务
     */
//    @AddSysLog(desc = "定时任务测试")  这个类有实现job，@AfterReturning无法读取到对应的注解
    public String quartzTest() {
        return "定时任务测试";
    }
    @Override
    public void execute(JobExecutionContext job) throws JobExecutionException {
//        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
//        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");
//        RedisTemplate redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
        QuartzInfoService quartzInfoService = (QuartzInfoService) SpringUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) SpringUtil.getBean("quartzLogService");
        RedisTemplate redisTemplate = (RedisTemplate) SpringUtil.getBean("redisTemplate");
        RedisLock redisLock = new RedisLock(redisTemplate,"quartzTest_lock_");
        try{
            if(redisLock.lock()) {
                QuartzLogEntity ql = new QuartzLogEntity();
                Map<String, Object> qiData = new HashMap<>();
                QuartzInfoEntity qi = quartzInfoService.findByCode("quartzTest");
                try {
                    qiData.put("id", qi.getId());
                    ql.setQuartzId(qi.getId());
                    ql.setStartTime(DateUtil.getNow());

                    String remark = quartzTest();

                    ql.setTime(DateUtil.getNow().getTime() - ql.getStartTime().getTime());
                    ql.setResult("10");
                    ql.setRemark(remark);
                    qiData.put("succeed", qi.getSucceed() + 1);

                } catch (Exception e) {
                    ql.setResult("20");
                    qiData.put("fail", qi.getFail() + 1);
                    log.error(e.getMessage(), e);
                } finally {
                    log.info("保存定时任务日志");
                    quartzLogService.save(ql);
                    quartzInfoService.update(qiData);
                }
            }
        } catch (Exception e) {
            log.info("quartzTest定时任务,redis锁发生异常:【{}】",e.getMessage());
        } finally {
            redisLock.unlock();
        }
    }
}
