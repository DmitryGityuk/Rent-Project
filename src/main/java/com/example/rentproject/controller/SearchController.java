package com.example.rentproject.controller;

import com.example.rentproject.models.House;
import com.example.rentproject.repository.HouseRepository;
import com.example.rentproject.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    private final HouseRepository houseRepository;

    private final UserRepository userRepository;

    public SearchController(HouseRepository houseRepository, UserRepository userRepository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/search")
    public String find(Model model) {
        model.addAttribute("houses", houseRepository.findAll());
        return "searchResult";
    }

    @GetMapping("/searchResult")
    public String findHome(@RequestParam("city") String city, Model model) {
        List<House> searchHouses = houseRepository.findByCity(city);
        model.addAttribute("city", city);
        model.addAttribute("searchHouses", searchHouses);
        return "searchResult";
    }
}
