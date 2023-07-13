package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.entities.Role;
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class MainController {

    private UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {

        User user = userService.findByUsername(principal.getName());
        return "user";


    }    @GetMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName());
        return "admin";
    }

}
