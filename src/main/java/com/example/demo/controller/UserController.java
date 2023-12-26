package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.Dao.UserRepository;
import com.example.demo.entity.User;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index(User user) {
        return "index";
    }

    @PostMapping("/submit")
    public String submit(User user) {
        // Simple server-side validation
        if (user.getName().isEmpty() || user.getEmail().isEmpty() || user.getAge() <= 0) {
            return "redirect:/?error=true";
        }

        userRepository.save(user);
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String view(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "view";
    }
}