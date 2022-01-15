package com.example.rentproject.repository;

import com.example.rentproject.models.House;
import com.example.rentproject.models.RentalRecord;
import com.example.rentproject.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RentalRecordRepository extends CrudRepository<RentalRecord, Long> {
    List<RentalRecord> findAllByUser(User user);

    List<RentalRecord> findAllByHouse(House house);
}
