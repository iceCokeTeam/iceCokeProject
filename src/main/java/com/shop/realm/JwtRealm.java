package com.shop.realm;

import com.auth0.jwt.interfaces.Claim;
import com.shop.token.JwtToken;
import com.shop.utils.TokenUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Map;

public class JwtRealm extends AuthorizingRealm {


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
        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
