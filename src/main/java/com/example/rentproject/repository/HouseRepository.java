package com.example.rentproject.repository;

import com.example.rentproject.models.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HouseRepository extends CrudRepository<House, Long> {

    House findByHouseName(String houseName);

    List<House> findByLocation(String location);

    List<House> findByCity(String city);

    Page<House> findAll(Pageable pageable);
}
