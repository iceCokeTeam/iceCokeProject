package com.shop.config;

import com.shop.filter.JwtFilter;
import com.shop.realm.AdminRealm;
import com.shop.realm.JwtRealm;
import com.shop.realm.UserRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro内置过滤器

        /*
            anon: 无需认证就可以访问
            authc: 必须认证了才能访问
            user: 必须拥有记住我功能才能使用
            perms: 拥有对某个资源的权限才能访问
            role: 拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/admin/login", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/doc.html", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/v2/**", "anon");
        filterMap.put("/webjars/**", "anon");
//        filterMap.put("/**", "authc");
        Map<String, Filter> filter = new HashMap<>();
        filter.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filter);
        filterMap.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        // 设置登录的请求
        shiroFilterFactoryBean.setLoginUrl("/user/login");

        // 设置未授权的请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/noAuth");
        return shiroFilterFactoryBean;
    }

    // 关联UserRealm
    @Bean(name = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("adminRealm") AdminRealm adminRealm, @Qualifier("userRealm") UserRealm userRealm, @Qualifier("jwtRealm")JwtRealm jwtRealm) {
        // 创建securityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置realm
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        List<Realm> realms = new ArrayList<>();
        realms.add(adminRealm);
        realms.add(userRealm);
        realms.add(jwtRealm);
        authenticator.setRealms(realms);
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        securityManager.setAuthenticator(authenticator);

        // 关闭session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    // 创建realm对象
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置MD5加密
        matcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        matcher.setHashIterations(1024);
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    @Bean
    public AdminRealm adminRealm() {
        AdminRealm adminRealm = new AdminRealm();
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置MD5加密
        matcher.setHashAlgorithmName("MD5");
        // 设置散列次数
        matcher.setHashIterations(1024);
        adminRealm.setCredentialsMatcher(matcher);
        return adminRealm;
    }

    @Bean
    public JwtRealm jwtRealm() {
        return new JwtRealm();
    }


}
