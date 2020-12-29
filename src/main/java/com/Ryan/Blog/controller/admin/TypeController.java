package com.Ryan.Blog.controller.admin;

import com.Ryan.Blog.pojo.Type;
import com.Ryan.Blog.service.TypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String showAllType(Model model,@RequestParam(value ="pageNum",defaultValue = "1")Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<Type> types = typeService.findAll();
        PageInfo<Type> pageInfo = new PageInfo<>(types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String toAdd(){
        return "admin/types-input";
    }

    @PostMapping("/types/add")
    public String addType(Type type, RedirectAttributes attributes){
        Type TypeByName = typeService.findByName(type.getName());
        if (TypeByName != null){
            attributes.addFlashAttribute("msg","该分类已存在");
            return "redirect:/admin/types/input";
        }
        typeService.addType(type);
        attributes.addFlashAttribute("msg","添加成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editType(Model model,@PathVariable("id") Integer id){
        Type byId = typeService.findById(id);
        model.addAttribute("type",byId);
        model.addAttribute("id",id);
        return "admin/types-update";
    }

    @PostMapping("/types/update")
    public String updateType(Type type,RedirectAttributes attributes){
        Type byName = typeService.findByName(type.getName());
        Integer id = type.getId();
        if (byName != null){
            attributes.addFlashAttribute("msg","该分类已存在");
            System.out.println("msg");
            return "redirect:/admin/types/"+id+"/input";
        }
        typeService.Update(type);
        attributes.addFlashAttribute("msg","更新成功");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String deleteType(@PathVariable("id") Integer id,RedirectAttributes attributes){
        typeService.deleteById(id);
        attributes.addFlashAttribute("msg","删除成功");
        return "redirect:/admin/types";
    }
}
