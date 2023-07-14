package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;

@Controller
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {

        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";


    }
    @GetMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("admin/allUsers")
    public String allUsers(Principal principal, Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "admin/allUsers";
    }

    @GetMapping("admin/users/{id}")
    public String show(Principal principal, @PathVariable long id, Model model) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "admin/user";
    }

    @GetMapping("admin/users/new")
    public String newUser(@ModelAttribute User user) {
        return "admin/new";
    }


    @PostMapping("admin/create")
    public String create(@ModelAttribute @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "new";
        userService.save(user);
        return "redirect:/admin";
    }


    @GetMapping("admin/users/{id}/edit")
    public String edit(Principal principal, Model model, @PathVariable long id) {
        User user = userService.findById(id).get();
        model.addAttribute("user", user);
        return "admin/edit";
    }


    @PostMapping("admin/users/{id}")
    public String update(@ModelAttribute @Valid User user, BindingResult bindingResult,
                         @PathVariable int id) {

        if (bindingResult.hasErrors())
            return "admin/users/{id}/edit";

        userService.update(id, user);
        return "redirect:/admin";
    }

    @PostMapping("admin/delete/{id}")
    public String delete(@PathVariable long id) {

        userService.deleteById(id);
        return "redirect:/admin";
    }

}
