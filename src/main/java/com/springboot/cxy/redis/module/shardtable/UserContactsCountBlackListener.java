package com.springboot.cxy.redis.module.shardtable;

import com.springboot.cxy.redis.module.shardtable.mapper.UserCountMapper;
import com.springboot.cxy.redis.module.shardtable.entity.UserCountEntity;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 用户通讯录黑名单统计事件处理.
 *
 * @author : xy.chen
 * @time : 2019/7/29
 */
@EnableAsync
@Component
public class UserContactsCountBlackListener {

    @Resource
    private UserCountMapper userCountMapper;

    /**
     * 用户通讯录黑名单统计事件处理
     *
     * @author : xy.chen
     * @time : 2019/7/29
     */
    @Async
    @EventListener(UserContactsCountEvent.class)
    public void processor(UserContactsCountEvent event) {
        final Long userId = event.getUserId();
        final int count = this.userCountMapper.countContactsBlack(userId, event.getTableName());
        UserCountEntity userCount = this.userCountMapper.findByUserId(userId);
        if (userCount == null) {
            //记录不存在，保存统计数据
            userCount = new UserCountEntity();
            userCount.setContactsBlackCount(count);
            userCount.setUserId(userId);
            this.userCountMapper.insert(userCount);
        } else if (count != userCount.getContactsBlackCount()) {
            //记录存在，且统计数据不一样的时候
            userCount.setContactsBlackCount(count);
            this.userCountMapper.update(userCount);
        }
    }

}
