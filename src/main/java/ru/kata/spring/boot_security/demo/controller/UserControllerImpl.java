package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserControllerImpl {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public String editUserForm(Model model) {
        User user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        System.out.println("check");
        model.addAttribute("user", user);
        model.addAttribute("listRoles", user.getUserRoles());
        return "user";
    }
}
