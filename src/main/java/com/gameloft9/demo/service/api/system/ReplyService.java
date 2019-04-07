package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.Reply;
import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.dataaccess.model.system.Whatever;

import java.util.List;

/**
 * Demo class
 *
 * @author xukailun
 * @date 2019/3/19
 *   信息表的接口方法
 */
public interface ReplyService {




  int add(Reply reply );

    /**
     * 修改
     * @param sysNotify 对这个对象进行update操作
     * */

    Boolean update(SysNotify sysNotify);




}
