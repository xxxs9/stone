package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysNotifyInfoMapper;
import com.gameloft9.demo.dataaccess.model.system.SysNotify;
import com.gameloft9.demo.dataaccess.model.system.SysNotifyInfo;
import com.gameloft9.demo.service.api.system.SysNotifyInfoService;
import com.gameloft9.demo.service.api.system.SysNotifyService;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysNotifyInfoServiceImpl implements SysNotifyInfoService {

@Autowired
SysNotifyInfoMapper dao;

    public int add(SysNotifyInfo sysNotifyInfo,SysNotify sysNotify) {
     /*   SysNotify s =new SysNotify();
        s.setId(UUIDUtil.getUUID());*/
     sysNotifyInfo.setId(sysNotify.getId());
        return dao.insert(sysNotifyInfo);
    }
}
