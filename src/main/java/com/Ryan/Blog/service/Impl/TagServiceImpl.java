package com.Ryan.Blog.service.Impl;

import com.Ryan.Blog.mapper.BlogMapper;
import com.Ryan.Blog.mapper.TagMapper;
import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.Tag;
import com.Ryan.Blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper mapper;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private TagService tagService;

    @Override
    public List<Tag> findAll() {
        List<Tag> tags = mapper.selectAll();
        return tags;
    }

    @Override
    public int addTag(Tag tag) {
        int i = mapper.insert(tag);
        return i;
    }

    @Override
    public Tag findByName(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        Tag tag1 = mapper.selectOne(tag);
        return tag1;
    }

    @Override
    public int updateTag(Tag tag) {
        int i = mapper.updateByPrimaryKey(tag);
        return i;
    }

    @Override
    public Tag findById(Integer id) {
        Tag tag = new Tag();
        tag.setId(id);
        Tag tag1 = mapper.selectOne(tag);
        return tag1;
    }

    @Override
    public int deleteById(Integer id) {
        Tag tag = new Tag();
        tag.setId(id);
        return mapper.delete(tag);
    }

    @Override
    public List<Tag> findAllShow() {
        List<Tag> tags = mapper.findAllTagAndBlog();
        for (Tag tag : tags) {
            List<Blog> blogs = tag.getBlogs();
            blogs.removeIf(blog -> blog.getPublished() != 1);
        }
        return  tags;
    }




}
