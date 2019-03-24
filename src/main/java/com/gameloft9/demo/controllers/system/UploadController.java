package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.FileUtil;
import com.gameloft9.demo.utils.PropertyUtil;
import com.gameloft9.demo.utils.QinniuUtil;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by gameloft9 on 2017/12/28.
 */
@Controller
@Slf4j
public class UploadController {


    /**
     * 上传文件
     * @param file 上传文件
     * @param type 文件业务类型 "userFace"-用户头像
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public IResult upload(MultipartFile file, String type, String fileName) throws Exception {
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(FileUtil.saveAndReturnUrl(file.getBytes(), Constants.AttachmentType.USER_FACE.value,fileName, PropertyUtil.getProperty("web_base_url"),PropertyUtil.getProperty("file_root_path")));
    }

    /**
     * 七牛云上传生成凭证
     *
     * @throws Exception
     */
    @RequestMapping("/QiniuUpToken")
    @ResponseBody
    public IResult QiniuUpToken(@RequestParam String suffix) throws Exception{
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String uptoken = QinniuUtil.getUptoken();
            result.put("token", uptoken);
            //存入外链默认域名，用于拼接完整的资源外链路径
            result.put("domain", "http://polmp1ze0.bkt.clouddn.com");

            // 是否可以上传的图片格式
            boolean flag = false;
            String[] imgTypes = new String[]{"jpg","jpeg","bmp","gif","png"};
            for(String fileSuffix : imgTypes) {
                if(suffix.substring(suffix.lastIndexOf(".") + 1).equalsIgnoreCase(fileSuffix)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                throw new Exception("图片：" + suffix + " 上传格式不对！");
            }

            //生成实际路径名
            String randomFileName = UUID.randomUUID().toString() + suffix;
            result.put("imgUrl", randomFileName);
            result.put("success", 1);
        } catch (Exception e) {
            result.put("message", "获取凭证失败，"+e.getMessage());
            result.put("success", 0);
        } finally {
            return new ResultBean<Map<String, Object>>(result);
        }
    }
}
