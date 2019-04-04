package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.model.system.TreeNode;
import com.gameloft9.demo.service.api.system.TreeNodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/03 2019-04-03
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TreeNodeServiceImpl implements TreeNodeService {


    @Override
    public List<TreeNode> getAllNode() {
        return null;
    }
}
