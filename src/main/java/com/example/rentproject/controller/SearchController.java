package com.example.rentproject.controller;

import com.example.rentproject.models.House;
import com.example.rentproject.models.HousePhoto;
import com.example.rentproject.repository.HousePhotoRepository;
import com.example.rentproject.repository.HouseRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    private final HouseRepository houseRepository;
    private final HousePhotoRepository housePhotoRepository;

    public SearchController(HouseRepository houseRepository, HousePhotoRepository housePhotoRepository) {
        this.houseRepository = houseRepository;
        this.housePhotoRepository = housePhotoRepository;
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

    @GetMapping("/singleSearch/{id}")
    public String findOneHouse(@PathVariable("id") Long id, Model model, HousePhoto photo){
//        model.addAttribute("photo", housePhotoRepository.findAllByHouse(id)); не работает
        model.addAttribute("house", houseRepository.findById(id).get());
        return "blog-single";
    }

}
