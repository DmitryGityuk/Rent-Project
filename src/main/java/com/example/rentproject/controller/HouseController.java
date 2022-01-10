package com.example.rentproject.controller;

import com.example.rentproject.models.House;
import com.example.rentproject.models.User;
import com.example.rentproject.repository.HouseRepository;
import com.example.rentproject.service.HouseService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class HouseController {
    private HouseRepository houseRepository;
    private HouseService houseService;

    public HouseController(HouseRepository houseRepository, HouseService houseService) {
        this.houseRepository = houseRepository;
        this.houseService = houseService;
    }

    @GetMapping("/houses")
    public String getAllHouses(House house, @AuthenticationPrincipal User user, BindingResult bindingResult, Model model){
        model.addAttribute("houses", house);
        return "houses";
    }

    @GetMapping("/addedHouse")
    public String getRegHouse(Model model){
        model.addAttribute("house", new House());
        return "houseInfo";
    }

    @PostMapping("/addedHouse")
    public String regHouse(@ModelAttribute("house") @Valid House house, BindingResult bindingResult, Model model, @AuthenticationPrincipal User user){
        if (bindingResult.hasErrors()){
            return "houseInfo";
        }
        if (!houseService.regHouse(house, user)){
            model.addAttribute("houseError", "такое жильё уже есть");
            return "houseInfo";
        }
        return "redirect:/houses";
    }

}