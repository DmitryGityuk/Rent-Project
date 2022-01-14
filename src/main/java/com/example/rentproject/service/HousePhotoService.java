package com.example.rentproject.service;

import com.example.rentproject.models.House;
import com.example.rentproject.models.HousePhoto;
import com.example.rentproject.repository.HousePhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class HousePhotoService {

    private HousePhotoRepository photoRepository;

    public HousePhotoService(HousePhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public void addPhoto(String uploadFile, HousePhoto photo, House house) {
        photo.setPhotoPath(uploadFile);
        photo.setHouse(house);
        photoRepository.save(photo);
    }
}
