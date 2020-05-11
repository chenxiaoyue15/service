package com.hzu.backstageservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserMsg {

    private Long id;
    private String msg;
    private Long userId;
    private Date createTime;
    private Long agentId;
    private String projectId;
    private String email;
    private String userName;


}
