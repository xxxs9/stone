package com.gameloft9.demo.mgrframework.handler;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;
import org.apache.shiro.authz.aop.PermissionAnnotationHandler;
import org.apache.shiro.subject.Subject;

import java.lang.annotation.Annotation;

/**
 * @author: 啊发包
 * @Date: 2019/04/11 2019-04-11
 */
public class MyPermissionHandler extends PermissionAnnotationHandler {
    public MyPermissionHandler(){

    }

    @Override
    public void assertAuthorized(Annotation a) throws AuthorizationException {
        if (a instanceof RequiresPermissions) {
            RequiresPermissions rpAnnotation = (RequiresPermissions)a;
            String[] perms = this.getAnnotationValue(a);
            Subject subject = this.getSubject();
            if (perms.length == 1) {
                subject.checkPermission(perms[0]);
            } else if (Logical.AND.equals(rpAnnotation.logical())) {
                this.getSubject().checkPermissions(perms);
            } else {
                if (Logical.OR.equals(rpAnnotation.logical())) {
                    boolean hasAtLeastOnePermission = false;
                    String[] var6 = perms;
                    int var7 = perms.length;

                    for(int var8 = 0; var8 < var7; ++var8) {
                        String permission = var6[var8];
                        if (this.getSubject().isPermitted(permission)) {
                            hasAtLeastOnePermission = true;
                        }
                    }

                    if (!hasAtLeastOnePermission) {
                        this.getSubject().checkPermission(perms[0]);
                    }
                }

            }
        }
    }
}
