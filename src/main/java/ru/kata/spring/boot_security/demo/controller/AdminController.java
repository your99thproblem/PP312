package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


@Controller
public class AdminController {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;


    @GetMapping(value = "/admin")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("listUser", userService.selectAllUsers());
        return mav;
    }

    @GetMapping("/admin/new_user")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", roleService.selectAllRoles());
        return "new_user";
    }

    @PostMapping("/admin/new_user")
    public String addUser(@ModelAttribute("user") User user,
                          @RequestParam("rolesToUser") String[] rolesToUsers) {
        System.out.println("check");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user, rolesToUsers);
        return "redirect:/admin";
    }

    @PostMapping(value = "/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        System.out.println("delete");
        return "redirect:/admin/";
    }

    @PostMapping("/admin/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("listRoles", roleService.selectAllRoles());
        return "edit_user";
    }

    @PostMapping("/admin/update/{id}")
    public String editUser(@PathVariable("id") Long id,
                           @RequestParam(value = "rolesToUser", required = false) String[] rolesToUsers,
                           User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.update(user, rolesToUsers);
        return "redirect:/admin";
    }
}
