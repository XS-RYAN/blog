package com.Ryan.Blog.service;

import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    int addTag(Tag tag);

    Tag findByName(String name);

    int updateTag(Tag tag);

    Tag findById(Integer id);

    int deleteById(Integer id);

    List<Tag> findAllShow();



}
