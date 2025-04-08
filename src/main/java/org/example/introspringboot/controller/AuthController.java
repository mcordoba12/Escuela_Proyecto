package org.example.introspringboot.controller;

import org.example.introspringboot.entity.Role;
import org.example.introspringboot.entity.User;
import org.example.introspringboot.service.RoleService;
import org.example.introspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/signup")
    public String signupGet(Model model) {
        List<Role> roles = roleService.getAllRoles();
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignup(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/home")
    public String home(){
        return "home";
    }


}
