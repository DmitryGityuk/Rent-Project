package com.example.rentproject.controller;

import com.example.rentproject.models.House;
import com.example.rentproject.models.HousePhoto;
import com.example.rentproject.models.User;
import com.example.rentproject.repository.HousePhotoRepository;
import com.example.rentproject.repository.HouseRepository;
import com.example.rentproject.service.AmazonClientService;
import com.example.rentproject.service.HousePhotoService;
import com.example.rentproject.service.HouseService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HouseController {
    private HouseRepository houseRepository;
    private HouseService houseService;
    private AmazonClientService amazonClient;
    private HousePhotoService photoService;

    public HouseController(HouseRepository houseRepository, HouseService houseService, AmazonClientService amazonClient, HousePhotoService photoService) {
        this.houseRepository = houseRepository;
        this.houseService = houseService;
        this.amazonClient = amazonClient;
        this.photoService = photoService;
    }

//    @GetMapping("/houses")
//    public String getAllHouses(@AuthenticationPrincipal User user, Model model) {
//        model.addAttribute("houses", houseRepository.findAll());
//        return "houses";
//    }

    @GetMapping("/houses")
    public String getAllHouses(@AuthenticationPrincipal User user, Model model) {
        return getOnePage(model, 1);
    }

    @GetMapping("/houses/page/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<House> page = houseService.findPage(currentPage);
        int totalPages = page.getTotalPages();
        long totalHouses = page.getTotalElements();
        List<House> houses = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalHouses", totalHouses);
        model.addAttribute("houses", houses);
        return "/houses";
    }

    @GetMapping("/addedHouse")
    public String getRegHouse(Model model) {
        model.addAttribute("house", new House());
        return "houseInfo";
    }

    @PostMapping("/addedHouse")
    public String regHouse(@ModelAttribute("house") @Valid House house, BindingResult bindingResult, Model model, @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            return "houseInfo";
        }
        if (!houseService.regHouse(house, user)) {
            model.addAttribute("houseError", "такое жильё уже есть");
            return "houseInfo";
        }
        return "redirect:/houses";
    }

    @GetMapping("/house/{id}")
    public String getUpdateHouse(@PathVariable("id") Long id, Model model) {
        model.addAttribute("house", houseRepository.findById(id).get());
        return "editHouse";
    }

    @PostMapping("/house/{id}")
    public String updateHouse(@PathVariable("id") Long id, House house, @AuthenticationPrincipal User user) {
        houseRepository.findById(id).get();
        houseService.updateHouse(house, user);
        return "redirect:/houses";
    }

    @GetMapping("/house/delete/{id}")
    public String deleteHouse(@PathVariable("id") Long id) {
        houseRepository.delete(houseRepository.findById(id).get());
        return "redirect:/houses";
    }

    @GetMapping("/uploadPhoto/{id}")
    public String getUploadPhoto(@PathVariable("id") Long id, Model model) {
        houseRepository.findById(id).get();
        model.addAttribute("photo", new HousePhoto());
        return "housePhoto";
    }

    @PostMapping("/uploadPhoto/{id}")
    public String uploadPhoto(@PathVariable("id") Long id,
                              @RequestParam("photo") MultipartFile file,
                              HousePhoto photo) {
        houseRepository.findById(id).get();
        amazonClient.uploadFile(file);
        photoService.addPhoto(amazonClient.uploadFile(file), photo, houseRepository.findById(id).get());
        return "redirect:/houses";
    }
}
