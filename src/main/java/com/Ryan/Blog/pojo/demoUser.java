package com.Ryan.Blog.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
//在编译阶段 会根据注解自动生产对应的方法  data包含了get/set/hashcode/equals/toString等
@Data
@Table(name = "user")
public class demoUser implements Serializable {

    @Id
    /*主键回填*/
    @KeySql(useGeneratedKeys = true)
    private Integer id;

    //用于绑定pojo成员变量 和 数据库表列名 ，如果一致可以不用写，会自动注入
    @Column(name = "username")
    private String name;

    private String password;
}
