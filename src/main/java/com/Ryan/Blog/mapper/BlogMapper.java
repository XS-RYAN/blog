package com.Ryan.Blog.mapper;

import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.BlogAndTag;
import com.Ryan.Blog.pojo.Tag;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogMapper extends Mapper<Blog> {

    List<Blog> findAll();

    List<Blog> findByCondition(Blog blog);

    int saveBlog(Blog blog);

    Blog findOneById(Long id);

    int updateBlog(Blog blog);

    List<Blog> findByTypeId(Integer id);

    List<Blog> findLikeTitle(String title);

    List<Blog> showBlogs();

    void updateViews(Long id,Long views);

    List<Blog> findBlogAndUserByTypeId(Integer id);

    void saveBlogAndTag(BlogAndTag blogAndTag);

    void removeBlogAndTag(BlogAndTag blogAndTag);

    Tag blogAndTypeAndTagAndUser(Integer id);

    List<Blog> recommendBlog();



}
