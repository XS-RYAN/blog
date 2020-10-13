package com.Ryan.Blog.mapper;

import com.Ryan.Blog.pojo.Comment;

import java.util.List;

public interface CommentMapper  {
    void saveComment(Comment comment);
    List<Comment> findByBlogId(Long id);
    List<Comment> findByParentCommentId(Integer id);
    Comment findById(Integer id);

    List<Integer> findAllCommentId(Long BlogId);

}
