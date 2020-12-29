package com.Ryan.Blog.controller;

import com.Ryan.Blog.pojo.Comment;
import com.Ryan.Blog.pojo.User;
import com.Ryan.Blog.service.BlogService;
import com.Ryan.Blog.service.CommentService;
import com.Ryan.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    private CommentService commentService;


    @GetMapping("comments/{id}")
    public String commentsShow(@PathVariable Long id, Model model){
        List<Comment> comments = commentService.findByBlogId(id);
        model.addAttribute("comments",comments);
        return "blog :: commentList";

    }

    @PostMapping("/comments")
    public String comment(Comment comment, HttpSession session){
        User user = (User) session.getAttribute("user");
        if (user != null){
            comment.setAdminComment(1);
            comment.setAvatar(user.getAvatar());
        }else {
            comment.setAdminComment(0);
        }
        commentService.saveComment(comment);
        return "redirect:/comments/"+comment.getBlogId();
    }

}
