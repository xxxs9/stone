package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TreeNode;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/03 2019-04-03
 */
public interface TreeNodeMapper {
    /**
     * 获取所有的父节点
     * */
    List<TreeNode> getAllNode();

    /**
     * 获取所有的子节点
     */
    List<TreeNode> getChildrenNode();
}
