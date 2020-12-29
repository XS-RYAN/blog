package com.Ryan.Blog.service;

import com.Ryan.Blog.mapper.BlogMapper;
import com.Ryan.Blog.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BlogService {
    List<Blog> findAll();

    List<Blog> findByCondition(Blog blog);

    Blog findByTitle(String Title);

    int saveBlog(Blog blog);

    Blog findOneById(Long id);

    int updateBlog(Blog blog);

    int deleteById(Long id);

    List<Blog> findByTypeId(Integer id);

    List<Blog> findLikeTitle(String Title);

    List<Blog> showBlogs();

    Blog showBlog(Long id);

    List<Blog> findBlogAndUserByTypeId(Integer id);

    List<Blog> BlogAndTypeAndTag();

    List<Blog> BlogAndTypeAndTagByTagId(Integer id);

    List<Blog> recommendBlog();
}
