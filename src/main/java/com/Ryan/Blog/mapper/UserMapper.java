package com.Ryan.Blog.mapper;

import com.Ryan.Blog.pojo.User;
import com.Ryan.Blog.pojo.demoUser;
import tk.mybatis.mapper.common.Mapper;


public interface  UserMapper extends Mapper<User> {
    User findById(Integer id);

}
