package com.Ryan.Blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class person {
    private String name;
    private Integer age;
    private List<String> test;
}
