package com.gameloft9.demo.dataaccess.model.system;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
/**
 * Demo class
 *
 * @author xukailun
 * @date 2019/3/19
 */
public class SysNotify implements Serializable {
    /**
    *id  标识该通知表的唯一主键
     */
    private String id;
    /**
     *creater  创建者
     */
    private String creater;
    /**
     *createTime  创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     *sendTime  发送时间时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;
    /**
     *receiverId  接受者编号
     */
    private String receiverId;
    /**
     *status  查看状态
     */
    private  String state;
    /**
     *descirbe  详细描述 为补充字段
     */
    private String  notifyDescribe;
    /**
     *level  通知优先等级
     */
    private String notifyLevel;
    /**
     *isReady  是否已读，补充字段
     */
    private String isReady;
    /**
     *senderId  发送者编号
     */
    private String senderId;
    /**
     *notofyInfoId  关联消息主题的ID
     */
    private String  notifyInfoId;

    /**
     *SysNotifyInfo  配置1对1 所需的实例化
     */
    private   SysNotifyInfo sysNotifyInfo;


}
