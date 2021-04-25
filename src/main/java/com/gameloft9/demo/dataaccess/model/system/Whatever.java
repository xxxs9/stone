package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Whatever {

    private  String creater;
    private  String receiverId;
    private String senderId;
    private String title;
    private String content;
    private  String targetId;
    private  String notifyType;
    private  String oldCount;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;


}
