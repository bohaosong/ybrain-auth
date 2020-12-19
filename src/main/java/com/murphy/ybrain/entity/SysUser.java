package com.murphy.ybrain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Murphy
 * @date 2020-12-10 13:51
 */
@Data
public class SysUser {

    private Long userId;
    private String username;
    private String password;
    private Integer statics;
    private String description;
    private Date createTime;
    private Date updateTime;
    private String openid;

}
