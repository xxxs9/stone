package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.ReplyMapper;
import com.gameloft9.demo.dataaccess.model.system.Reply;
import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.service.api.system.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyMapper dao;

    public int add(Reply reply) {
        return dao.insert(reply);
    }

    public Boolean update(SysNotify sysNotify) {
        return null;
    }
}
