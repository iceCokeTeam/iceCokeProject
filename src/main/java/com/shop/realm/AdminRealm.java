package com.shop.realm;

import com.shop.pojo.Admin;
import com.shop.service.AdminService;
import com.shop.token.CustomizedToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class AdminRealm extends AuthorizingRealm {

    @Resource
    private AdminService adminService;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof CustomizedToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("233admin");
        CustomizedToken token = (CustomizedToken) authenticationToken;
        System.out.println("admin");
        if (token.getLoginType().equals("admin")) {
            Admin admin = adminService.selectAdminByName(token.getUsername());
            if (admin == null)
                return null;

            return new SimpleAuthenticationInfo(admin, admin.getPassword(), ByteSource.Util.bytes("abc"), getName());
        }
        return null;
    }
}
