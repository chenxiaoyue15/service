package com.hzu.community.api.model;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class User {


    private Integer id;
    private String name;
    private Integer age;
    private String pwd;
    private String token;
    private String avatarUrl;
    private String accountId;
    private String phone;       //电话

    private String email;        //电子邮件

    private String aboutme;     //自我介绍

    private String passwd;    //密码

    private String confirmPasswd;  //确认密码

    private Integer type;        //1.普通用户 2.人事

    private String avatar;       //头像图片地址

    private Date createTime;     //创建时间

    private Integer enable;      //是否启用 1.启用 0.停用

    @JSONField(deserialize = false,serialize = false)
    private MultipartFile avatarFile;   //接收用户上传文件

    private String newPasswd;          //用于修改密码的时候

    private String key;          //激活链接有一个key

    private Long companyId;      //人事所属公司


    private String enableUrl;
}


