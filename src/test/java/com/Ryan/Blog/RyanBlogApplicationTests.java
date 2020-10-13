package com.Ryan.Blog;

import com.Ryan.Blog.mapper.ArchivesMapper;
import com.Ryan.Blog.mapper.CommentMapper;
import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.Comment;
import com.Ryan.Blog.pojo.person;
import com.Ryan.Blog.service.BlogService;
import com.Ryan.Blog.service.TypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;

import java.time.LocalTime;
import java.util.*;

@SpringBootTest
class RyanBlogApplicationTests {

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void test(){
        List<Comment> comments = commentMapper.findByBlogId(1577879724089L);
        ArrayList<Comment> fatherComment = new ArrayList<>();

        //获得父评论的集合
        for (Comment comment : comments) {
            if (comment.getParentCommentId() == -1) {
                fatherComment.add(comment);
            }
        }

        combineChildren(fatherComment);
        for (Comment comment : fatherComment) {
            List<Comment> sonComments = comment.getSonComments();
            for (Comment son : sonComments) {
                son.setParentComment(commentMapper.findById(son.getParentCommentId()));
            }
        }
        System.out.println(fatherComment);

    }


    @Test
    public void  combineChildren(List<Comment> comments){
        for (Comment comment : comments) {
            List<Comment> replys1 = getSon(comment);
            for(Comment reply1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setSonComments(tempReplys);
            //清除临时存放区
            tempReplys = new ArrayList<>();
        }
    }

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    @Test
    public void  recursively(Comment comment){
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (getSon(comment).size()>0) {
            List<Comment> replys = getSon(comment);
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (getSon(reply).size()>0) {
                    recursively(reply);
                }
            }
        }
    }

    @Test
    public List<Comment> getSon(Comment comment){
        return commentMapper.findByParentCommentId(comment.getId());
    }


    @Test
    public void test5(){
        List<Comment> byParentCommentId = commentMapper.findByParentCommentId(51);
        System.out.println(byParentCommentId.size());
    }





}
