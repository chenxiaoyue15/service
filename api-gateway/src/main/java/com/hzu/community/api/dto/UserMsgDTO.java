package com.hzu.community.api.dto;

import com.hzu.community.api.model.Project;
import lombok.Data;

import java.util.Date;

@Data
public class UserMsgDTO {

    private Long id;
    private String msg;
    private Long userId;
    private Date createTime;
    private Long agentId;
    private Long projectId;
    private String email;
    private String userName;
    private Project project;

}
