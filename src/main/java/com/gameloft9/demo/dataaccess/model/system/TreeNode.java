package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/03 2019-04-03
 */
@Data
public class TreeNode {
    /**id*/
    private String id;

    /**节点名称*/
    private String title;

    /**节点value，隐藏*/
    private String value;

    /**checked:默认false，不选中*/
    public boolean checked;

    /**是否可用，默认可用false*/
    public boolean disabled;

    /**父节点ID*/
    private String parentId;


    /**子节点*/
    private List<TreeNode> data;

}
