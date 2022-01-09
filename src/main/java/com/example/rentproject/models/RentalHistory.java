package com.example.rentproject.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "rental_history", uniqueConstraints = {
        @UniqueConstraint(name = "USER_ROLE_UK", columnNames = {"USER_ID", "RENTAL_RECORD_ID"})
})
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class RentalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RENTAL_HISTORY_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RENTAL_RECORD_ID", nullable = false)
    private RentalRecord rentalRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

}