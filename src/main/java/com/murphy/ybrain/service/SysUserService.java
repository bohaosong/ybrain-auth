package com.murphy.ybrain.service;

import com.murphy.ybrain.entity.SysPermission;
import com.murphy.ybrain.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Murphy
 * @date 2020-12-10 14:00
 */
@Repository
public class SysUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //根据账号查询用户信息
    public SysUser getUserByUsername(String username){
        String sql = "select * from sys_user where username = ?";
        //连接数据库查询用户
        List<SysUser> list = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(SysUser.class));
        if(list !=null && list.size()==1){
            return list.get(0);
        }
        return null;
    }

    //根据账号查询用户信息
    public SysUser getUserByopenId(String openId){
        String sql = "select * from sys_user where openid = ?";
        //连接数据库查询用户
        List<SysUser> list = jdbcTemplate.query(sql, new Object[]{openId}, new BeanPropertyRowMapper<>(SysUser.class));
        if(list !=null && list.size()==1){
            return list.get(0);
        }
        return null;
    }

    //根据用户id查询用户权限
    public List<String> findPermissionsByUserId(Long userId){
        String sql = "SELECT * FROM sys_permission WHERE permission_id IN(\n" +
                "SELECT permission_id FROM sys_role_permission WHERE role_id IN(\n" +
                "SELECT role_id FROM sys_user_role WHERE user_id = ?\n" +
                ")\n" +
                ")";

        List<SysPermission> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(SysPermission.class));
        List<String> permissions = new ArrayList<>();
        list.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }

    /**
     *
     * 获取 code
     * http://localhost:53020/auth/oauth/authorize?client_id=c1&response_type=code&scope=all&redirect_uri=http://www.baidu.com
     */

    /**
     *
     * 获取 token
     * http://localhost:53020/auth/oauth/authorize?client_id=c1&response_type=token&scope=all&redirect_uri=http://www.baidu.com
     *
     */

}
