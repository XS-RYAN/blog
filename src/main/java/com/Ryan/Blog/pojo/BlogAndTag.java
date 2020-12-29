package com.Ryan.Blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_blog_tags")
public class BlogAndTag {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Integer tag_id;
    private Long blog_id;


    public BlogAndTag(Integer tag_id, Long blog_id) {
        this.tag_id = tag_id;
        this.blog_id = blog_id;
    }

    public BlogAndTag(Long blog_id) {
        this.blog_id = blog_id;
    }
}
