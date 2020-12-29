package com.Ryan.Blog.mapper;

import com.Ryan.Blog.pojo.Tag;
import com.Ryan.Blog.pojo.Type;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TagMapper extends Mapper<Tag> {
    List<Tag> findAllTagAndBlog();
}
