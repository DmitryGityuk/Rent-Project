package com.example.rentproject.controller;

import com.example.rentproject.models.User;
import com.example.rentproject.repository.HouseRepository;
import com.example.rentproject.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final UserService userService;
    private final HouseRepository houseRepository;

    public MainController(UserService userService, HouseRepository houseRepository) {
        this.userService = userService;
        this.houseRepository = houseRepository;
    }

    @GetMapping({"/", "/index"})
    public String goHome(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("house", houseRepository.findAll());
        if (user != null) {
            model.addAttribute("user", user.getUserEmail());
        }
        return "index";
    }

    @PostMapping("/index")
    public String sendNewsMessage(@RequestParam String email) {
        userService.sendNews(email);
        return "index";
    }

    @GetMapping("/sign-in")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @GetMapping("/logoutSuccessful")
    public String logoutSuccessfulPage() {
        return "index";
    }

    @GetMapping("/other")
    public String otherPage() {
        return "simplePage";
    }
}

