package com.example.rentproject.controller;

import com.example.rentproject.models.House;
import com.example.rentproject.models.RentalRecord;
import com.example.rentproject.models.User;
import com.example.rentproject.repository.HousePhotoRepository;
import com.example.rentproject.repository.HouseRepository;
import com.example.rentproject.repository.RentalRecordRepository;
import com.example.rentproject.service.RentalRecordService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {
    private final HouseRepository houseRepository;
    private final HousePhotoRepository housePhotoRepository;
    private final RentalRecordService recordService;
    private final RentalRecordRepository recordRepository;

    public SearchController(HouseRepository houseRepository, HousePhotoRepository housePhotoRepository, RentalRecordService recordService, RentalRecordRepository recordRepository) {
        this.houseRepository = houseRepository;
        this.housePhotoRepository = housePhotoRepository;
        this.recordService = recordService;
        this.recordRepository = recordRepository;
    }

    @GetMapping("/search")
    public String find(Model model) {
        model.addAttribute("houses", houseRepository.findAll());
        return "searchResult";
    }

    @GetMapping("/searchResult")
    public String findHome(@RequestParam("city") String city, Model model) {
        List<House> searchHouses = houseRepository.findByCity(city);
        if (searchHouses.isEmpty()) {
            model.addAttribute("message", "К сожалению в этом городе объекты еще не зарегистрированы");
        }
        model.addAttribute("city", city);
        model.addAttribute("searchHouses", searchHouses);
        return "searchResult";
    }

    @GetMapping("/singleSearch/{id}")
    public String findOneHouse(@PathVariable("id") Long id, Model model) {
        model.addAttribute("record", new RentalRecord());
        model.addAttribute("house", houseRepository.findById(id).get());
        return "blog-single";
    }

    @PostMapping("/singleSearch/{id}")
    public String addRental(@PathVariable("id") Long id, @AuthenticationPrincipal User user, RentalRecord record, House house, Model model) {
        recordService.addNewRecord(record, user, house);
        return "redirect:/profile";
    }
}
