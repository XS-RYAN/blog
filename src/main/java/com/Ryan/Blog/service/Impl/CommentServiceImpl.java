package com.Ryan.Blog.service.Impl;

import com.Ryan.Blog.mapper.CommentMapper;
import com.Ryan.Blog.pojo.Comment;
import com.Ryan.Blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Transactional
    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());

        commentMapper.saveComment(comment);
    }


    @Override
    public List<Comment> findByBlogId(Long id) {
        List<Comment> comments = commentMapper.findByBlogId(id);
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

        return fatherComment;

    }

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

    private List<Comment> tempReplys = new ArrayList<>();

    public void  recursively(Comment comment) {
        tempReplys.add(comment);//顶节点添加到临时存放集合
        if (getSon(comment).size() > 0) {
            List<Comment> replys = getSon(comment);
            for (Comment reply : replys) {
                tempReplys.add(reply);
                if (getSon(reply).size() > 0) {
                    recursively(reply);
                }
            }
        }
    }

    public List<Comment> getSon(Comment comment){
        return commentMapper.findByParentCommentId(comment.getId());
    }
}
