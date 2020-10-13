package com.Ryan.Blog.service;

import com.Ryan.Blog.pojo.Comment;

import java.util.List;

public interface CommentService {
    void saveComment(Comment comment);
    List<Comment> findByBlogId(Long id);
}
