package com.Ryan.Blog.controller;


import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.Type;
import com.Ryan.Blog.service.BlogService;
import com.Ryan.Blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller

public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types")
    public String types(Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,@RequestParam(value = "typeId",defaultValue = "0")Integer typeId){
        List<Type> types = typeService.findAllShow();

        PageHelper.startPage(pageNum,5);
        List<Blog> blogs;
        if (typeId == 0){
            blogs = blogService.showBlogs();
        } else {
            blogs =  blogService.findBlogAndUserByTypeId(typeId);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("typeId",typeId);
        return "types";
    }



}
