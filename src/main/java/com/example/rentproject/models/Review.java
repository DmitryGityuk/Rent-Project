package com.example.rentproject.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "reviews", uniqueConstraints = {
        @UniqueConstraint(name = "REVIEWS_UK", columnNames = {"USER_ID", "HOUSE_ID"})
})
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REVIEWS_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID", nullable = false)
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @Column(name = "REVIEW", nullable = false, length = 300)
    private String review;
}