package com.shop.realm;

import com.shop.pojo.Admin;
import com.shop.pojo.User;
import com.shop.service.AdminService;
import com.shop.utils.CustomizedToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class AdminRealm extends AuthorizingRealm {

    @Resource
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        // 设置当前用户的权限
        authorizationInfo.addStringPermission("/admin/register");
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        CustomizedToken token = (CustomizedToken) authenticationToken;
        if (token.getLoginType().equals("admin")) {
            Admin admin = adminService.selectAdminByName(token.getUsername());
            if (admin == null)
                return null;

            return new SimpleAuthenticationInfo(admin, admin.getPassword(), ByteSource.Util.bytes("abc"), getName());
        }
        return null;
    }
}
