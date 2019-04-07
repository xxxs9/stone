package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.Perms;
import com.gameloft9.demo.dataaccess.model.system.TreeNode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/03 2019-04-03
 */
public interface TreeNodeService {

    /**
     * 获取所有的父节点
     * */
    List<TreeNode> getAllNode(String page,String limit,String belongToName);

    /**
     * 总条数
     * @param belongToName
     * @return
     */
    int getCount(String belongToName);

    /**
     * 初始化所属资源
     * */

    List<TreeNode> initBelongTo();

    /**
     * 添加权限
     * */

    String addPerms(Perms perms);

    /**
     * 根据id获取treeNode
     */
    Perms getById(String id);

    /**更新treeNode*/
    Boolean updatePerms(String id,String belongToName,String name, String perms,String parentId,List<String> roleIdList);

    /**删除*/
    Boolean deletePerms(String id);

}
