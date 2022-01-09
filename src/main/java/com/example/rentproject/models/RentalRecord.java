package com.example.rentproject.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "rental_record", uniqueConstraints = {
        @UniqueConstraint(name = "RENTAL_RECORD_UK", columnNames = {"USER_ID", "HOUSE_ID"})
})
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString

public class RentalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECORD_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID", nullable = false)
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "RENTAL_DATE", nullable = false)
    private Instant rentalDate;

    @Column(name = "RETURN_DATE", nullable = false)
    private Instant returnDate;

    @Column(name = "PIN_CODE", nullable = false, length = 4)
    private String pinCode;
}