package com.example.rentproject.service;

import com.example.rentproject.models.House;
import com.example.rentproject.models.User;
import com.example.rentproject.repository.HouseRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class HouseService {
    private final HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public boolean regHouse(House house, @AuthenticationPrincipal User user){
        House houseFromDB = houseRepository.findByHouseName(house.getHouseName());
        if (houseFromDB != null){
            return false;
        }
        house.setHouseName(house.getHouseName());
        house.setLocation(house.getLocation());
        house.setPrice(house.getPrice());
        house.setRooms(house.getRooms());
        house.setGuests(house.getGuests());
        house.setDescription(house.getDescription());
        house.setType(house.getType());
        house.setUser(user);
        house.setHouseIsReserved(false);
        houseRepository.save(house);
        return true;
    }
}