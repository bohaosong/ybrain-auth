package com.murphy.ybrain.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Murphy
 * @date 2020-12-10 13:55
 */
@Data
public class SysPermission {

    private Long permissionId;
    private String code;
    private String description;
    private String url;
    private Integer statics;
    private Date createTime;
    private Date updateTime;

}
