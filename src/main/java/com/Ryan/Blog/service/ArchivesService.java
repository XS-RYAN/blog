package com.Ryan.Blog.service;

import com.Ryan.Blog.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface ArchivesService {
    Map<String, List<Blog>> blogAndYear();

    Integer BlogCount();
}
