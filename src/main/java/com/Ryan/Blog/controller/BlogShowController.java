package com.Ryan.Blog.controller;

import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.Comment;
import com.Ryan.Blog.pojo.Type;
import com.Ryan.Blog.service.BlogService;
import com.Ryan.Blog.service.CommentService;
import com.Ryan.Blog.service.TypeService;
import com.Ryan.Blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogShowController {


    @Autowired
    private BlogService blogService;

    @Autowired
    private CommentService commentService;


    @GetMapping("/{id}/show")
    public String showBlog(@PathVariable("id") Long id, Model model){
        Blog ById = blogService.showBlog(id);
        String content = MarkdownUtils.markdownToHtmlExtensions(ById.getContent());
        ById.setContent(content);
        List<Comment> comments = commentService.findByBlogId(id);
        model.addAttribute("comments",comments);
        model.addAttribute("blog",ById);


        return "blog";
    }




}
