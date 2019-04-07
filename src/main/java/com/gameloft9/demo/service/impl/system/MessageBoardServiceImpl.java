package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.MessageBoardMapper;
import com.gameloft9.demo.dataaccess.dao.system.ReplyMapper;
import com.gameloft9.demo.dataaccess.model.system.MessageBoard;
import com.gameloft9.demo.dataaccess.model.system.Reply;
import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.service.api.system.MessageBoardService;
import com.gameloft9.demo.service.api.system.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MessageBoardServiceImpl implements  MessageBoardService{
@Autowired
MessageBoardMapper dao;

    public int add(MessageBoard messageBoard) {
        return dao.insert(messageBoard);
    }

    public Boolean update(SysNotify sysNotify) {
        return null;
    }
}
