package com.hzu.community.api.dto;

import lombok.Data;

@Data
public class CommentCreateDTO {
    private Integer parentId;
    private String content;
    private Integer type;
    private Integer commentCount;
}
