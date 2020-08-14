package com.shop.realm;

import com.auth0.jwt.interfaces.Claim;
import com.shop.mapper.UserMapper;
import com.shop.pojo.Admin;
import com.shop.pojo.User;
import com.shop.service.AdminService;
import com.shop.service.UserService;
import com.shop.token.JwtToken;
import com.shop.utils.TokenUtil;
import io.swagger.models.auth.In;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.Map;

public class JwtRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private AdminService adminService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        if (token == null)
            return null;
        Map<String, Claim> stringClaimMap = TokenUtil.verifyToken(token);
        if (stringClaimMap == null || stringClaimMap.isEmpty() || stringClaimMap.get("userId") == null) {
            throw new IncorrectCredentialsException();
        }
        if (stringClaimMap.get("type").equals("admin")) {
            Integer id = stringClaimMap.get("userId").asInt();
            Admin admin = adminService.selectAdminById(id);
            if (admin.getStatus() == 0) {
                throw new IncorrectCredentialsException();
            }
        }
        else if(stringClaimMap.get("type").equals("user"))  {
            Integer id = stringClaimMap.get("useId").asInt();
            User user = userService.selectUser(id);
            if (user.getStatus() == 0) {
                throw new IncorrectCredentialsException();
            }
        }
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
