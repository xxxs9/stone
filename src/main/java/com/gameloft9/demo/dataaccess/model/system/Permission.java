package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/06 2019-04-06
 */
@Data
public class Permission {
    /**所属资源*/
    private String belongTo;
    /**资源名称*/
    private String name;
    /**资源代号*/
    private String perms;
    /**角色列表*/
    private String roleName;
}
