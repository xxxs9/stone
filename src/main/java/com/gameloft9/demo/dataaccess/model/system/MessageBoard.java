package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.security.Principal;
import java.security.PrivateKey;
import java.util.List;

@Data
/**
 * Demo class
 *
 * @author MessageBoard
 * @date 2019/03/21
 */
public class MessageBoard {

    /*id 留言板标识ID */
    private  String  id;
    /*notifyInfoId 关联通知的关联外键盘 */
    private String notifyInfoId;
    /*messageBoardId 关联回复表的外键 */
    private  String messageBoardId;

    private List<Reply>reply;

}
