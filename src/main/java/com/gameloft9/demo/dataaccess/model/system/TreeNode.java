package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/03 2019-04-03
 */

@Data
public class TreeNode {

    private String id;
    private String belongToId;
    private String belongToName;
    private String name;
    private String parentId;
    private List<TreeNode> children;
    private boolean notParent;
    private String perms;


}
