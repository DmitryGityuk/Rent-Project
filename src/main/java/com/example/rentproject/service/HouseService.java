package com.example.rentproject.service;

import com.example.rentproject.models.House;
import com.example.rentproject.models.User;
import com.example.rentproject.repository.HouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public boolean regHouse(House house, String file, @AuthenticationPrincipal User user) {
        House houseFromDB = houseRepository.findByHouseName(house.getHouseName());
        if (houseFromDB != null) {
            return false;
        }
        house.setHouseName(house.getHouseName());
        house.setCity(house.getCity());
        house.setCountry(house.getCountry());
        house.setLocation(house.getCity() + ", " +  house.getCountry());
        house.setPrice(house.getPrice());
        house.setRooms(house.getRooms());
        house.setGuests(house.getGuests());
        house.setDescription(house.getDescription());
        house.setType(house.getType());
        house.setUser(user);
        house.setHouseIsReserved(false);
        house.setMainPhoto(file);
        houseRepository.save(house);
        return true;
    }

    public void updateHouse(House house, User user) {
        house.setHouseName(house.getHouseName());
        house.setCity(house.getCity());
        house.setCountry(house.getCountry());
        house.setLocation(house.getCity() + ", " +  house.getCountry());
        house.setPrice(house.getPrice());
        house.setRooms(house.getRooms());
        house.setGuests(house.getGuests());
        house.setDescription(house.getDescription());
        house.setType(house.getType());
        house.setUser(user);
        house.setHouseIsReserved(false);
        houseRepository.save(house);
    }

    public Page<House> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber -1, 2);
        return houseRepository.findAll(pageable);
    }
}
