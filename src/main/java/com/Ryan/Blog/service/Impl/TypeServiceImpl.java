package com.Ryan.Blog.service.Impl;

import com.Ryan.Blog.mapper.BlogMapper;
import com.Ryan.Blog.mapper.TypeMapper;
import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.Type;
import com.Ryan.Blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper mapper;

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Type> findAll() {
        List<Type> types = mapper.selectAll();
        return types;
    }

    @Override
    public void addType(Type type) {
        mapper.insert(type);
    }

    @Override
    public Type findByName(String name) {
        Type type = new Type();
        type.setName(name);
        Type type1 = mapper.selectOne(type);
        return type1;
    }

    @Override
    public Type findById(Integer id) {
        Type type = new Type();
        type.setId(id);
        Type type1 = mapper.selectOne(type);
        return type1;
    }

    @Override
    public void Update(Type type) {
        mapper.updateByPrimaryKey(type);
    }

    @Override
    public void deleteById(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Type> findAllShow() {
        List<Type> types = findAll();

        for (Type type : types) {
            Integer typeId = type.getId();
            List<Blog> blogs = blogMapper.findByTypeId(typeId);
            blogs.removeIf(blog -> blog.getPublished() != 1);
            type.setBlogs(blogs);
        }
        return types;
    }
}
