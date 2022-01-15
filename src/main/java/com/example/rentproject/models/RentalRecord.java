package com.example.rentproject.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "rental_record", uniqueConstraints = {
        @UniqueConstraint(name = "RENTAL_RECORD_UK", columnNames = {"USER_ID", "HOUSE_ID"})
})
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class RentalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RECORD_ID", nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID", nullable = false)
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @Column(name = "RENTAL_DATE", nullable = false)
    private Date rentalDate;

    @DateTimeFormat(pattern = "YYYY-MM-dd")
    @Column(name = "RETURN_DATE", nullable = false)
    private Date returnDate;

    @Column(name = "PIN_CODE", nullable = false, length = 4)
    private String pinCode;
}