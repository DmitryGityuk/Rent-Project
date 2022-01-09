package com.example.rentproject.controller;

import com.example.rentproject.models.User;
import com.example.rentproject.repository.UserRepository;
import com.example.rentproject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profileUser(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "generalProfile";
    }

    @GetMapping("/updateUserInfo/{id}")
    public String userInfo(@PathVariable("id") Long id, @AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", userRepository.findById(id).get());
        return "profileUserInfo";
    }

    @PostMapping("/updateUserInfo/{id}")
    public String updateUserInfo(@PathVariable("id") Long id, @ModelAttribute("user") @AuthenticationPrincipal User user){
        userService.updateUser(id, user);
        return "redirect:/profile";
    }

}
