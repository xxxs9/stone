package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: 啊发包
 * @Date: 2019/04/06 2019-04-06
 */
@Data
public class Perms {

    private String id;
    private String parentId;
    private String name;
    private String perms;
    private List<String> roleIdList;
    private String belongToName;

}
