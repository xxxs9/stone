package com.gameloft9.demo.utils;

import com.qiniu.util.Auth;

/**
 * @author: Sxiu
 * @create: 2019/3/19 21:20
 * @description:
 */
public class QinniuUtil {

    public static String getUptoken(){
        //获取上传凭证token
        String accessKey = "7fR62mN4sLJmr0xznLEdwgDPAuAcQfk2GjVTHx1i";
        String secretKey = "Ok6Jz_KSOvbMdNMjtnzGGROY1Ah5cHddp2lySjQl";
        String bucket = "tanghuafeng";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }

}
