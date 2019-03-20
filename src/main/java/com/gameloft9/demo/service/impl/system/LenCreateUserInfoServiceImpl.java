package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenCreateUserInfoMapper;
import com.gameloft9.demo.dataaccess.model.system.LenCreateUserInfo;
import com.gameloft9.demo.service.api.system.LenCreateUserInfoService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.impl.system
 * @author: Lennon_Yuan
 * @time: 2019/3/20 0020 - 上午 11:32
 * @description:
 */
@Service
public class LenCreateUserInfoServiceImpl implements LenCreateUserInfoService {
    @Autowired
    LenCreateUserInfoMapper mapper;

    @Override
    public List<LenCreateUserInfo> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenCreateUserInfo getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenCreateUserInfo> selectByPage(String page, String limit, String createUser, String createTime) {
        PageRange pageRange = new PageRange(page, limit);
        return mapper.selectByPage(pageRange.getStart(), pageRange.getEnd(), createUser, createTime);
    }

    @Override
    public boolean insert(LenCreateUserInfo len) {
        String uuid = UUIDUtil.getUUID();
        LenCreateUserInfo len1 = new LenCreateUserInfo();
        len1.setId(uuid);
        len1.setCreateUser(len.getCreateUser());
        len1.setCreateTime(len.getCreateTime());
        len1.setEmployeeId(len.getEmployeeId());

        if (mapper.insert(len1) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(LenCreateUserInfo len) {
        if (mapper.update(len) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (mapper.delete(id) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int dataCount(String createUser) {
        return mapper.dataCount(createUser);
    }

    @Override
    public boolean insertSelective(LenCreateUserInfo len) {
        if (mapper.insertSelective(len) > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(LenCreateUserInfo len) {
        if (mapper.updateByPrimaryKeySelective(len) > 0) {
            return true;
        } else {
            return false;
        }
    }
}
