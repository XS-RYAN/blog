package com.Ryan.Blog.controller;


import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.Tag;
import com.Ryan.Blog.pojo.Type;
import com.Ryan.Blog.service.BlogService;
import com.Ryan.Blog.service.TagService;
import com.Ryan.Blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller

public class TagShowController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/tags")
    public String tags(Model model,@RequestParam(value = "tagId",defaultValue = "0")Integer tagId,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum){
        List<Tag> tags = tagService.findAllShow();
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = tagId == 0 ? blogService.BlogAndTypeAndTag() : blogService.BlogAndTypeAndTagByTagId(tagId);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("tagId",tagId);
        return "tags";

    }



}
