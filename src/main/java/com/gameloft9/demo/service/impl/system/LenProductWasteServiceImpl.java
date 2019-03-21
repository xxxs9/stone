package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.LenProductWasteMapper;
import com.gameloft9.demo.dataaccess.model.system.LenProductWaste;
import com.gameloft9.demo.service.api.system.LenProductWasteService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @packageName: com.gameloft9.demo.service.impl.system
 * @author: Lennon_Yuan
 * @time: 2019/3/19 0019 - 下午 4:57
 * @description:
 */
@Service
public class LenProductWasteServiceImpl implements LenProductWasteService {
    @Autowired
    LenProductWasteMapper mapper;
    @Override
    public List<LenProductWaste> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public LenProductWaste getByPrimaryKey(String id) {
        return mapper.getByPrimaryKey(id);
    }

    @Override
    public List<LenProductWaste> selectByPage(String page, String limit, String state) {
        PageRange pageRange = new PageRange(page, limit);
        return mapper.selectByPage(pageRange.getStart(),pageRange.getEnd(),state);
    }

    @Override
    public boolean insert(LenProductWaste lenProduct) {
        if (mapper.insert(lenProduct)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean update(LenProductWaste lenProduct) {
        if (mapper.update(lenProduct)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (mapper.delete(id)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int dataCount(String state) {
        return mapper.dataCount(state);
    }

    @Override
    public boolean changeState() {

        if (mapper.changeState()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateByPrimaryKeySelective(LenProductWaste lenProduct) {
        if (mapper.updateByPrimaryKeySelective(lenProduct)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean insertSelective(LenProductWaste lenProduct) {
        if (mapper.insertSelective(lenProduct)>0){
            return true;
        }else{
            return false;
        }
    }
}
