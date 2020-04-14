package com.hzu.community.model;

import lombok.Data;

@Data
public class QuestionQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
    private Integer offset;
}
