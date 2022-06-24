package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        User user = new User();

        List<User> lu = userService.selectAllUsers();
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("listUser", lu);
        return mav;
    }

    @GetMapping("/admin/new_user")
    public String newUser(Model model) {
        Collection<Role> lr = roleService.selectAllRoles();
        Long rolesToUsers = null;
        model.addAttribute("user", new User());
        model.addAttribute("listRoles", lr);
        model.addAttribute("rolesToUser", rolesToUsers);
        return "new_user";
    }

    @PostMapping("/admin/new_user")
    public String addUser(@ModelAttribute("user") User user,
                          @ModelAttribute("rolesToUser") Long rolesToUsers) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addRole(user, rolesToUsers);
        userService.saveUser(user);
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
        Collection<Role> lr = roleService.selectAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", lr);
        return "edit_user";
    }

    @PostMapping("/admin/update/{id}")
    public String editUser(@PathVariable("id") Long id,
                           @ModelAttribute("rolesToUser") Long rolesToUsers,
                           User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addRole(user, rolesToUsers);
        userService.update(user);
        return "redirect:/admin";
    }
    @PostMapping(value = "/admin/deleteroles/{id}")
    public String deleteRolesUser(@PathVariable("id") Long id) {
        userService.deleteRolesOfUser(id);
        System.out.println("delete");
        return "redirect:/admin";
    }
}
