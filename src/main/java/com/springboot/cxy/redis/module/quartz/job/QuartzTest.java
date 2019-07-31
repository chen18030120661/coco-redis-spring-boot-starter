package com.springboot.cxy.redis.module.quartz.job;

import com.springboot.cxy.redis.module.quartz.entity.QuartzInfoEntity;
import com.springboot.cxy.redis.module.quartz.entity.QuartzLogEntity;
import com.springboot.cxy.redis.module.quartz.service.QuartzInfoService;
import com.springboot.cxy.redis.module.quartz.service.QuartzLogService;
import com.springboot.cxy.redis.module.quartz.util.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import tool.util.BeanUtil;
import tool.util.DateUtil;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Lazy(value = false)
public class QuartzTest implements Job {

    /**
     * 定时任务
     */
    public String quartzTest() {
        return "定时任务测试";
    }
    @Override
    public void execute(JobExecutionContext job) throws JobExecutionException {
        QuartzInfoService quartzInfoService = (QuartzInfoService) BeanUtil.getBean("quartzInfoService");
        QuartzLogService quartzLogService = (QuartzLogService) BeanUtil.getBean("quartzLogService");

        RedisTemplate redisTemplate = (RedisTemplate) BeanUtil.getBean("redisTemplate");
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
            log.info("渠道日报统计，redis锁发生异常");
        } finally {
            redisLock.unlock();
        }
    }
}
