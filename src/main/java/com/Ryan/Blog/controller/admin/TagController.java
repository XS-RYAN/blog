package com.Ryan.Blog.controller.admin;

import com.Ryan.Blog.pojo.Tag;
import com.Ryan.Blog.service.Impl.TagServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagServiceImpl service;

    @GetMapping("/tags")
    public String showAllTag(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, Model model){
        PageHelper.startPage(pageNum,5);
        List<Tag> tags = service.findAll();
        PageInfo<Tag> pageInfo = new PageInfo<>(tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

    @GetMapping("/tags/input")
    public String toAdd(){
        return "admin/tags-input";
    }

    @PostMapping("/tags/add")
    public String addTag(Tag tag, RedirectAttributes attributes){
        Tag byName = service.findByName(tag.getName());
        if (byName != null) {
            attributes.addFlashAttribute("msg","该标签已存在");
            return "redirect:/admin/tags/input";
        }
        int i = service.addTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("msg", "添加失败");
        }else {

            attributes.addFlashAttribute("msg", "添加成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String toUpdate(@PathVariable("id") Integer id,Model model){
        model.addAttribute("id",id);
        Tag byId = service.findById(id);
        model.addAttribute("tag",byId);
        return "admin/tags-update";
    }

    @PostMapping("tags/update")
    public String toUpdate(Tag tag,RedirectAttributes attributes){
        System.out.println(tag);
        Tag byName = service.findByName(tag.getName());
        if (byName != null) {
            attributes.addFlashAttribute("msg","该标签已存在");
            return "redirect:/admin/tags/"+tag.getId()+"/input";
        }

        int i = service.updateTag(tag);
        if (i == 0){
            attributes.addFlashAttribute("msg","更新失败");
        }else {
            attributes.addFlashAttribute("msg","更新成功");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable("id") Integer id,RedirectAttributes attributes){
        int i = service.deleteById(id);
        if (i == 0){
            attributes.addFlashAttribute("msg","删除失败");
        }else {
            attributes.addFlashAttribute("msg","删除成功");
        }
        return "redirect:/admin/tags";
    }

}
