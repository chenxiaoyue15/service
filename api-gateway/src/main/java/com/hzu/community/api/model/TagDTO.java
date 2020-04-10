package com.hzu.community.api.model;

import lombok.Data;

import java.util.List;

@Data
public class TagDTO {
    private String className;
    private List<String> tags;
}
