package com.example.rentproject.repository;

import com.example.rentproject.models.HousePhoto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HousePhotoRepository extends CrudRepository<HousePhoto, Long>{

    List<HousePhoto> findAllByHouse(Long id);

}
