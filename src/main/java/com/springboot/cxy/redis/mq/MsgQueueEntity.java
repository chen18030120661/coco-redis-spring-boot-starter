package com.springboot.cxy.redis.mq;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : xy.chen
 * @time : 2019/7/24
 * @desc : 消息队列实体类
 */
@Data
@ApiModel("消息队列实体类")
public class MsgQueueEntity implements Serializable {
    private static final long serialVersionUID = 4789318742306732523L;

    @ApiModelProperty(value = "主键", hidden = true)
    private Long id;  //主键ID

    @ApiModelProperty("账号")
    private String loginName;

    @ApiModelProperty("密码")
    private String loginPwd;

}
