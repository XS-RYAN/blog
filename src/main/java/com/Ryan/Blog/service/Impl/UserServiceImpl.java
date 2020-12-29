package com.Ryan.Blog.service.Impl;

import com.Ryan.Blog.mapper.UserMapper;
import com.Ryan.Blog.pojo.User;
import com.Ryan.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper mapper;
    @Override
    public User checkUser(String username,String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User acquiredUser = mapper.selectOne(user);
        return acquiredUser;
    }

    @Override
    public User findById(Integer id) {
        User user = mapper.findById(id);
        return user;
    }

}
