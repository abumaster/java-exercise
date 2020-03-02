package com.abumaster.springbootdemocrud.config;

import com.abumaster.springbootdemocrud.auth.dto.UserDto;
import com.abumaster.springbootdemocrud.auth.service.UserManagerService;
import com.abumaster.springbootdemocrud.bookstore.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    @Lazy
    UserManagerService userManagerService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String userName=(String) authenticationToken.getPrincipal();
        User user = userManagerService.getUserByName(userName);
        log.info("realm认证:{}",userName);
        if(null==user){
            throw new UnknownAccountException("账号不存在");
        }

        return new SimpleAuthenticationInfo(userName,user.getPassWord(),getName());
    }
}
