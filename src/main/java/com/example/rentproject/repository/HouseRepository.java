package com.example.rentproject.repository;

import com.example.rentproject.models.House;
import org.springframework.data.repository.CrudRepository;

public interface HouseRepository extends CrudRepository<House, Long> {

    House findByHouseName(String houseName);

    House findByLocation(String location);
}