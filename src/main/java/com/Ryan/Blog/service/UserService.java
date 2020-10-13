package com.Ryan.Blog.service;

import com.Ryan.Blog.pojo.User;

public interface UserService {
    User checkUser(String username,String password);

    User findById(Integer id);
}
