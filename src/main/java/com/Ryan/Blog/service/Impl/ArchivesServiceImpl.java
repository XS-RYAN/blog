package com.Ryan.Blog.service.Impl;

import com.Ryan.Blog.mapper.ArchivesMapper;
import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.service.ArchivesService;
import com.Ryan.Blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArchivesServiceImpl implements ArchivesService {

    @Autowired
    private ArchivesMapper archivesMapper;

    @Autowired
    private BlogService blogService;


    @Override
    public Map<String, List<Blog>> blogAndYear() {
        List<String> year = archivesMapper.findAllYear();
        Map<String, List<Blog>> map = new HashMap<>();
        for (String s : year) {
            List<Blog> blogs = archivesMapper.findBlogByYear(s);
            map.put(s,blogs);
        }
        return map;
    }

    @Override
    public Integer BlogCount() {
        List<Blog> all = blogService.findAll();
        int count = all.size();
        return count;
    }


}
