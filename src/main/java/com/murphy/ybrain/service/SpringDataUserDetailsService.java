package com.murphy.ybrain.service;

import com.alibaba.fastjson.JSON;
import com.murphy.ybrain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Administrator
 * @version 1.0
 **/
@Service
public class SpringDataUserDetailsService implements UserDetailsService {

    @Autowired
    SysUserService sysUserService;

    //根据 账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //将来连接数据库根据账号查询用户信息
        SysUser sysUser = sysUserService.getUserByUsername(username);
        if(sysUser == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        //根据用户的id查询用户的权限
        List<String> permissions = sysUserService.findPermissionsByUserId(sysUser.getUserId());
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //将userDto转成json
        String principal = JSON.toJSONString(sysUser);
        UserDetails userDetails = User.withUsername(principal).password(sysUser.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }

    public UserDetails loadUserByOpenId(String openId) throws UsernameNotFoundException {

        //将来连接数据库根据账号查询用户信息
        SysUser sysUser = sysUserService.getUserByopenId(openId);
        if(sysUser == null){
            //如果用户查不到，返回null，由provider来抛出异常
            return null;
        }
        //根据用户的id查询用户的权限
        List<String> permissions = sysUserService.findPermissionsByUserId(sysUser.getUserId());
        //将permissions转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        //将userDto转成json
        String principal = JSON.toJSONString(sysUser);
        UserDetails userDetails = User.withUsername(principal).password(sysUser.getPassword()).authorities(permissionArray).build();
        return userDetails;
    }
}
