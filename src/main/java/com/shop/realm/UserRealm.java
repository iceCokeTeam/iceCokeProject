package com.shop.realm;

import com.shop.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();

        // 设置当前用户的权限
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = new User();
        if (user == null)
            return null;

        // 密码认证，shiro做
        // 参数1：数据库名, 参数2：数据库md5+salt后的密码, 参数3：注册时的随机盐, 参数4：realm的名字
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes("abc"), getName());
    }
}
