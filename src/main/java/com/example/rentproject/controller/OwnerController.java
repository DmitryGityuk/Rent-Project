package com.example.rentproject.controller;

import com.example.rentproject.models.User;
import com.example.rentproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class OwnerController {
    private final UserService userService;

    public OwnerController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/owner")
    public String gerOwner(Model model) {
        model.addAttribute("owner", new User());
        return "ownerRegistration";
    }

    @PostMapping("/owner")
    public String regOwner(@ModelAttribute("owner") @Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ownerRegistration";
        }
        if (!userService.regOwner(user)) {
            model.addAttribute("ownerError", "Пользователь уже существует!");
            return "ownerRegistration";
        }
        return "redirect:sign-in";
    }
}
