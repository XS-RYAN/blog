package com.Ryan.Blog.mapper;

import com.Ryan.Blog.pojo.Blog;

import java.util.List;

public interface ArchivesMapper {
    List<String> findAllYear();

    List<Blog> findBlogByYear(String year);
}
