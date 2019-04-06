package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.Perms;
import com.gameloft9.demo.dataaccess.model.system.TreeNode;
import org.apache.ibatis.annotations.Param;

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

    /**
     * 初始化所属资源
     * */
    List<TreeNode> initBelongTo();

    /**
     * 添加权限
     * */
    void addPerms(TreeNode treeNode);

    /**
     * 根据id获取treeNode
     */
    TreeNode getById(@Param("id") String id);

    /**更新treeNode*/
    void updatePerms(TreeNode treeNode);

    /**删除*/
    void deletePerms(@Param("id") String id);
}
