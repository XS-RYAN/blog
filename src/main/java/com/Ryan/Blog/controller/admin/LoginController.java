package com.Ryan.Blog.controller.admin;

import com.Ryan.Blog.pojo.User;
import com.Ryan.Blog.service.UserService;
import com.Ryan.Blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model){
        User user = userService.checkUser(username,MD5Util.code(password));
        if (user != null) {
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }
        model.addAttribute("msg","账号密码错误，本站暂未开通注册，需要账号请联系管理员开通");
        return "admin/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/login";
    }



}
