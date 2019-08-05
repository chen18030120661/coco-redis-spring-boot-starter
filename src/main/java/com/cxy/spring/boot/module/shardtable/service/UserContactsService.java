package com.cxy.spring.boot.module.shardtable.service;

import com.cxy.spring.boot.module.shardtable.UserContactsCountEvent;
import com.cxy.spring.boot.module.shardtable.entity.UserContactsEntity;
import com.cxy.spring.boot.module.shardtable.mapper.UserContactsMapper;
import com.cxy.spring.boot.module.shardtable.util.ShardTableUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserContactsService {

    @Resource
    private UserContactsMapper userContactsMapper;

    @Resource
    private ApplicationEventPublisher publisher;

    /**
     * 创建分表并保存数据
     * @param infos
     * @param userId
     * @return
     */
    public boolean shardTable(List<Map<String, Object>> infos, long userId) {
        int msg = 0;
        String name = null;
        String phone = null;
        boolean flag = false;

        // 分表
        final String tableName = ShardTableUtil.generateTableNameById("cl_user_contacts", userId, 30000);
        int countTable = userContactsMapper.countTable(tableName);
        if (countTable == 0) {
            userContactsMapper.createTable(tableName);
        }
        for (Map<String, Object> map : infos) {
            log.debug("保存用户userId："+userId+"通讯录，name："+ StringUtil.isNull(map.get("name"))+"，phone："+StringUtil.isNull(map.get("phone")));
            name = StringUtil.isNull(map.get("name")).replaceAll("(null)", "").replace("()", "");
            phone = StringUtil.isNull(map.get("phone")).replaceAll("-", "").replaceAll(" ", "");
            log.debug("保存用户userId："+userId+"通讯录，name___："+name+"，phone___："+phone);
            if (StringUtil.isNotBlank(phone)) {
                phone = phone.replaceAll("\\+86|-| ", "");
                if (phone.startsWith("0086") || phone.startsWith("086")) {
                    phone = phone.replace("0086", "").replace("086", "");
                }
            }
            if(StringUtil.isNotBlank(name) && name.length() <= 20 && StringUtil.isNotBlank(phone) && phone.length() <= 20){
                try {
                    UserContactsEntity userContacts = new UserContactsEntity();
                    Map<String, Object> params = new HashMap<>();
                    params.put("userId",userId);
                    params.put("phone",phone);
                    List<UserContactsEntity> resultList = userContactsMapper.listShardSelective(tableName,params);
                    if(resultList!=null && resultList.size()>0){
                        continue;
                    }
                    userContacts.setUserId(userId);
                    userContacts.setName(name);
                    userContacts.setPhone(phone);
                    msg = userContactsMapper.saveShard(tableName, userContacts);
                } catch (Exception e) {
                    log.error("保存用户userId："+userId+"通讯录异常， name： " + name + "， phone：" + phone);
                }
            } else {
                log.error("保存用户userId："+userId+"通讯录失败，name： " + name + "， phone：" + phone);
            }
        }
        if (msg>0) {
            flag = true;
        }
        //发送用户黑名单统计事件（异步执行，不影响用户通讯录保存）
        this.publisher.publishEvent(new UserContactsCountEvent(userId, tableName));
        return flag;
    }
}
