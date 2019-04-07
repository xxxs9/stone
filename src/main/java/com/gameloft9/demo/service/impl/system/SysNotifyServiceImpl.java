package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysNotifyInfoMapper;
import com.gameloft9.demo.dataaccess.dao.system.SysNotifyMapper;
import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.dataaccess.model.system.SysNotifyInfo;
import com.gameloft9.demo.dataaccess.model.system.WebSocketTest;
import com.gameloft9.demo.dataaccess.model.system.Whatever;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.SysNotifyService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import javafx.scene.chart.PieChart;
import okhttp3.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class SysNotifyServiceImpl  implements SysNotifyService {

    @Autowired
 SysNotifyMapper dao ;
    @Autowired
    SysNotifyInfoMapper sysNotifyInfoMapper;
    @Autowired
    WebSocketTest webSocketTest;



    public List<SysNotify> getAll(String page, String limit, String creater, String receiverId, String state) {
        PageRange pageRange = new PageRange(page, limit);

        return dao.getAll(pageRange.getStart(),pageRange.getEnd(),creater,receiverId,state);
    }

    @Override
    public List<SysNotify> findOutBox(String loginName) {
        return dao.findOutBox(loginName);
    }

    public List<SysNotify> findSysNofiyForAll(String loginName) {
        return dao.findSysNofiyForAll(loginName);
    }


    public List<SysNotify> findAllReply() {
        return dao.findAllReply();
    }

    public int getNewMessage(String loginName) {
        return dao.getNewMessage(loginName);
    }


    public SysNotify findMessage(String id) {

        return dao.findMessage(id);
    }

    public SysNotify sendmessage(String id) {
        return dao.replyMessage(id);
    }

    public SysNotify selectByPrimaryKey(String id) {
        SysNotify s  =dao.selectByPrimaryKey(id);
        return s;
    }

    public int countGetAll(String creater, String receiverId, String status) {
        return dao.countGetAll(creater,receiverId,status);
    }

    public int hideForByPrimaryKey(String id) {


        return  dao.hideForByPrimaryKey(id);
    }

    public Boolean deleteById(String id) {
        CheckUtil.notBlank(id,"用户id为空");

         dao.deleteByPrimaryKey(id);
         return true;
    }

    public int add(SysNotify sysNotify) {
        return 0;
    }

    public int add(Whatever whatever) throws IOException {
/* 主表*/

        whatever.setSendTime(new Date());

        String[] split = whatever.getReceiverId().split(",");

        for (int i=0;i<split.length;i++){
            SysNotify  sysNotify =new SysNotify();
            SysNotifyInfo sysNotifyInfo =new SysNotifyInfo();
            sysNotify.setId(UUIDUtil.getUUID());
            sysNotify.setNotifyInfoId(UUIDUtil.getUUID());
            sysNotify.setCreater(whatever.getCreater());
            sysNotify.setReceiverId(split[i]);
            sysNotify.setSenderId(whatever.getSenderId());
            sysNotify.setSendTime(whatever.getSendTime());
            sysNotifyInfo.setId(sysNotify.getNotifyInfoId());
            sysNotifyInfo.setTitle(whatever.getTitle());
            sysNotifyInfo.setContent(whatever.getContent());
            String senderId =sysNotify.getSenderId();
            String receiverId =sysNotify.getReceiverId();
            dao.insert(sysNotify);

              sysNotifyInfoMapper.insert(sysNotifyInfo);
            webSocketTest.sendMessage("收到"+sysNotify.getSenderId()+"一条消息",sysNotify);
        }



return  23333;







    }



    public Boolean update(SysNotify sysNotify) {

          SysNotify s =dao.selectByPrimaryKey(sysNotify.getId());
           s.setSenderId(s.getSenderId());
           s.setState(s.getState());
           s.setSendTime(s.getSendTime());
           s.setNotifyLevel(s.getNotifyLevel());
           s.setReceiverId(s.getReceiverId());
           s.setNotifyDescribe(s.getNotifyDescribe());
           s.setIsReady(s.getIsReady());
           s.setCreateTime(s.getCreateTime());
           s.setCreater(s.getCreater());

           dao.updateByPrimaryKey(s);
        return true;
    }
}
