package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;
import org.apache.shiro.session.Session;

import java.io.Serializable;

@Data
/**
 * Demo class
 *
 * @author xukailun
 * @date 2019/3/19
 */
public class SysNotifyInfo implements Serializable{
    /**
     *id  通知信息主键
     */
    private String id;
    /**
     *targetId 信息对象，例如文章的ID
     */
    private String targetId;
    /**
     *title 信息对象，例如文章的ID
     */
    private String title;
    /**
     *content 信息内容
     */
    private String content;

    /**
     *content 信息类别 比如1 是评价功能
     */
    private String  notifyType;


    public  SysNotify sysNotify;

    private MessageBoard messageBoard;


    public SysNotify getSysNotify() {
        return sysNotify;
    }

    public void setSysNotify(SysNotify sysNotify) {
        this.sysNotify = sysNotify;
    }

}
