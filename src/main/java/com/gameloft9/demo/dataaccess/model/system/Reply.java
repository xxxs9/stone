package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
/**
 * Demo class
 *
 * @author xukailun
 * @date 2019/03/22
 */
public class Reply {
    /* *
  id 回复主键
*/
    private String id;
    /* *
messageBoardId 对应留言板的外键
*/
    private String messageBoardId;
    /* *
replyContent 回复内容
*/
    private String  replyContent;

    /* *
replyTime 回复时间
*/
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  replyTime;
   /* *
replyId 回复者ID
*/

    private String  replyId;


}
