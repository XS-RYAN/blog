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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;


@Controller
public class IndexController {

    @Autowired
    private TagService tagService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private BlogService blogService;


    @RequestMapping("/")
    public String toIndex(Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        List<Tag> tags = tagService.findAll();
        List<Type> types = typeService.findAll();
        for (Type type : types) {
            Integer id = type.getId();
            List<Blog> ByTypeId = blogService.findByTypeId(id);
            ByTypeId.removeIf(blog -> blog.getPublished() != 1);
            type.setBlogs(ByTypeId);
        }
        types.removeIf(type -> type.getBlogs().size() < 1);


        List<Blog> recommendBlog = blogService.recommendBlog();


        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogService.showBlogs();
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);

        model.addAttribute("tags",tags);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendBlog",recommendBlog);
        return "index";
    }

    @RequestMapping("/search")
    public String search(String title,Model model,@RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogService.findLikeTitle(title);
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("title",title);
        return "search";
    }


}
