package com.Ryan.Blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_type")
public class Type {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String name;

    private List<Blog> blogs = new ArrayList<>();
}
