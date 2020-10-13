package com.Ryan.Blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_blog")
public class Blog {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    //标记
    private String flag;
    //浏览次数
    private Integer views;
    //赞赏开启
    private Integer appreciation;
    //转载声明开启
    private Integer shareStatement;
    //评论开启
    private Integer commentAble;
    //是否发布
    private Integer published;
    //是否推荐
    private Integer recommend;
    //博客描述
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date create_time;
    @Temporal(TemporalType.TIMESTAMP)
    private Date update_time;
    private String tagIds;
    private String typeId;
    private List<Tag> tags = new ArrayList<>();
    private Type type;
    private Integer userId;
    private User user;
    private List<Comment> comments = new ArrayList<>();


}
