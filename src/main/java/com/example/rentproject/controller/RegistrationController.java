package com.example.rentproject.controller;

import com.example.rentproject.models.User;
import com.example.rentproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller

public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String getRegister(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/sign-up")
    public String addNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!userService.regUser(user)) {
            model.addAttribute("userEmailError", "Пользователь уже существует!");
            return "registration";
        }
        return "redirect:/sign-in";
    }

    @GetMapping("/activate/{code}")
    public String activateUser(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "User is activated");
        } else {
            model.addAttribute("message", "Code not found");
        }
        return "redirect:/sign-in";
    }
}
