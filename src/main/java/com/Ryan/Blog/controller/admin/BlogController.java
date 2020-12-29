package com.Ryan.Blog.controller.admin;

import com.Ryan.Blog.pojo.*;
import com.Ryan.Blog.service.BlogService;
import com.Ryan.Blog.service.TagService;
import com.Ryan.Blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String showAllBlog(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Blog> blogs = blogService.findAll();
        for (Blog blog : blogs) {
            String typeId = blog.getTypeId();
            Type type = typeService.findById(Integer.valueOf(typeId));
            blog.setType(type);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo",pageInfo);
        List<Type> types = typeService.findAll();
        model.addAttribute("types",types);
        return "admin/blogs";
    }

    @PostMapping("/blogs/search")
    public String search(String title,String typeId,String recommend,Model model){
        Blog blog = new Blog();
        blog.setTitle("%"+title+"%");
        blog.setTypeId(typeId);
        blog.setRecommend(recommend != null ? 1: 0);
        List<Blog> byCondition = blogService.findByCondition(blog);
        for (Blog blog1 : byCondition) {
            blog1.setType(typeService.findById(Integer.valueOf(blog1.getTypeId())));
            System.out.println(blog1);
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(byCondition);
        List<Type> types = typeService.findAll();
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("title",title);
        model.addAttribute("typeId",typeId);
        model.addAttribute("recommend",recommend);
        return "admin/blogs";
    }

    @GetMapping("/blogs/toAdd")
    public String toAdd(Model model){
        List<Type> types = typeService.findAll();
        List<Tag> tags = tagService.findAll();
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return "admin/blogs-input";
    }

    @PostMapping("/blogs/add")
    public String add(Blog blog, String isRecommend, String shareInfo, String isAppreciation, String isComment,
                      RedirectAttributes attributes, HttpSession session){
        blog.setRecommend(isRecommend != null ? 1:0);
        blog.setShareStatement(shareInfo != null ? 1:0);
        blog.setAppreciation(isAppreciation != null ? 1:0);
        blog.setCommentAble(isComment != null ? 1:0);
        User user = (User) session.getAttribute("user");
        blog.setUserId(user.getId());
        blog.setViews(0);
        blog.setCreate_time(new Date());
        int i = blogService.saveBlog(blog);
        if (i == 1){
            attributes.addFlashAttribute("msg","新增成功");
        }else {
            attributes.addFlashAttribute("msg","新增失败");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("/blogs/{id}/toUpdate")
    public String toUpdate(@PathVariable("id") Long id,Model model){

        List<Type> types = typeService.findAll();
        List<Tag> tags = tagService.findAll();
        Blog blog = blogService.findOneById(id);
        model.addAttribute("blog",blog);
        model.addAttribute("types",types);
        model.addAttribute("tags",tags);
        return "admin/blogs-update";
    }

    @PostMapping("/blogs/update")
    public String update(Blog blog,String isRecommend, String shareInfo, String isAppreciation, String isComment,RedirectAttributes attributes){
        blog.setRecommend(isRecommend != null ? 1:0);
        blog.setShareStatement(shareInfo != null ? 1:0);
        blog.setAppreciation(isAppreciation != null ? 1:0);
        blog.setCommentAble(isComment != null ? 1:0);
        blog.setUpdate_time(new Date());
        System.out.println(blog);
        int i = blogService.updateBlog(blog);
        if (i == 1){
            attributes.addFlashAttribute("msg","更新成功");
        }else {
            attributes.addFlashAttribute("msg","更新失败");
        }
        return "redirect:/admin/blogs";
    }

    @GetMapping("blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id,RedirectAttributes attributes){
        int i = blogService.deleteById(id);
        if (i == 1){
            attributes.addFlashAttribute("msg","删除成功");
        }else {
            attributes.addFlashAttribute("msg","删除失败");
        }
        return "redirect:/admin/blogs";
    }
}
