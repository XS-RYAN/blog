package com.Ryan.Blog.controller;

import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.service.ArchivesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ArchivesController {

    @Autowired
    private ArchivesService service;


    @GetMapping("/archives")
    public String archives(Model model){
        Map<String, List<Blog>> map = service.blogAndYear();
        Integer count = service.BlogCount();
        model.addAttribute("map",map);
        model.addAttribute("count",count);
        return "archives";
    }
}
