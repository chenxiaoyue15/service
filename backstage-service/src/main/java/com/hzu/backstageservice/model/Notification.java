package com.hzu.backstageservice.model;

import lombok.Data;

@Data
public class Notification {
    private Integer id;
    private Integer notifier;
    private Integer receiver;
    private Integer outerId;
    private Long gmtCreate;
    private Integer type;
    private Integer status;
    private String notifierName;
    private String outerTitle;

}
