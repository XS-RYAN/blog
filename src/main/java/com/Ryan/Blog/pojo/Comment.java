package com.Ryan.Blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_comment")
public class Comment {
    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private Blog blog;
    private Long blogId;

    private Comment parentComment;
    private Integer parentCommentId;

    private Integer adminComment;

    private List<Comment> sonComments;
}
