package com.Ryan.Blog;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;



@SpringBootApplication
//扫描mybatis所有的业务接口  （不写这个的话需要在每个mapper接口上 写mapper标签 效果一样）
@MapperScan("com.Ryan.Blog.mapper")
@EnableEncryptableProperties
public class RyanBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(RyanBlogApplication.class, args);
    }



}
