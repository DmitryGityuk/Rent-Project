package com.example.rentproject.repository;

import com.example.rentproject.models.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {
}
