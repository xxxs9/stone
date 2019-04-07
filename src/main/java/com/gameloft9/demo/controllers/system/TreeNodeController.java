package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.Perms;
import com.gameloft9.demo.dataaccess.model.system.TreeNode;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.TreeNodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    TreeNodeService treeNodeService;

    @RequestMapping(value = "/treeNode",method = RequestMethod.POST)
    @ResponseBody
    public IResult treeNode(String page,String limit,String belongToName){
        return new PageResultBean<Collection<TreeNode>>(treeNodeService.getAllNode(page,limit,belongToName),treeNodeService.getCount(belongToName));
    }

    @RequestMapping(value = "/initBelongTo", method = RequestMethod.POST)
    @ResponseBody
    public IResult initBelongTo(){
        return new ResultBean<Collection<TreeNode>>(treeNodeService.initBelongTo());
    }

    @RequestMapping(value = "/addPerms" ,method = RequestMethod.POST)
    @ResponseBody
    public IResult addPerms(@RequestBody Perms perms){
        return new ResultBean<String>(treeNodeService.addPerms(perms));
    }

    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public IResult get(String id){
        return new ResultBean<Perms>(treeNodeService.getById(id));
    }

    /**
     * 更新菜单
     * @param
     * @return
     */
    @RequestMapping(value = "/UpdatePerms.do",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("perms:update")
    public IResult updatePerms(@RequestBody Perms perms){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(treeNodeService.updatePerms(perms.getId(),perms.getBelongToName(),perms.getName(),perms.getPerms(),perms.getParentId(),perms.getRoleIdList()));
    }

    /**
     * 删除
     * @param id
     * @return
     */

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("perms:delete")
    public IResult delete(String id){
        return new ResultBean<Boolean>(treeNodeService.deletePerms(id));
    }
}
