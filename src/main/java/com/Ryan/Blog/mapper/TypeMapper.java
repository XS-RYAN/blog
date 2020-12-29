package com.Ryan.Blog.mapper;

import com.Ryan.Blog.pojo.Tag;
import com.Ryan.Blog.pojo.Type;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TypeMapper extends Mapper<Type> {

    List<Tag> findTagAndBlogByTagId(Integer id);
}
