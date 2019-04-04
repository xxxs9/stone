package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.TreeNode;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: 啊发包
 * @Date: 2019/04/03 2019-04-03
 */
@Controller
@RequestMapping("/node")
public class TreeNodeController {

    @RequestMapping(value = "/treeNode",method = RequestMethod.POST)
    @ResponseBody
    public IResult treeNode(){
        return new ResultBean<Collection<TreeNode>>();
    }
}
