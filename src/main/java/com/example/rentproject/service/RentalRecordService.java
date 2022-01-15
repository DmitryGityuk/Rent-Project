package com.example.rentproject.service;

import com.example.rentproject.models.House;
import com.example.rentproject.models.RentalRecord;
import com.example.rentproject.models.User;
import com.example.rentproject.repository.RentalRecordRepository;
import com.example.rentproject.utils.RandomPinUtils;
import org.springframework.stereotype.Service;

@Service
public class RentalRecordService {
    private final RentalRecordRepository recordRepository;

    public RentalRecordService(RentalRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void addNewRecord(RentalRecord record, User user, House house) {
        record.setUser(user);
        record.setHouse(house);
        record.setPinCode(RandomPinUtils.UUIDPinCode());
        recordRepository.save(record);
    }
}
