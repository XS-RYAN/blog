package com.Ryan.Blog.service;

import com.Ryan.Blog.pojo.Type;

import java.util.List;

public interface TypeService {
    List<Type> findAll();

    void addType(Type type);

    Type findByName(String name);

    Type findById(Integer id);

    void Update(Type type);

    void deleteById(Integer id);

    List<Type> findAllShow();
}
