package com.example.rentproject.repository;

import com.example.rentproject.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByUsername(String userName);

    User findUserByActivationCode(String code);

    User findUserByUserEmail(String userEmail);
}
