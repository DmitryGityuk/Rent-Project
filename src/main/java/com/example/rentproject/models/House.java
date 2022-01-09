package com.example.rentproject.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "house", uniqueConstraints = {
        @UniqueConstraint(name = "HOUSE_UK", columnNames = "HOUSE_NAME")
})
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HOUSE_ID", nullable = false)
    private Long id;

    @Column(name = "HOUSE_NAME", nullable = false, length = 128)
    private String houseName;

    @Column(name = "TYPE", nullable = false, length = 36)
    private String type;

    @Column(name = "ROOMS", nullable = false, length = 128)
    private String rooms;

    @Column(name = "GUESTS", nullable = false, length = 128)
    private String guests;

    @Column(name = "PRICE", nullable = false, length = 128)
    private String price;

    @Column(name = "LOCATION", nullable = false, length = 300)
    private String location;

    @Column(name = "DESCRIPTION", nullable = false, length = 300)
    private String description;

    @Column(name = "HOUSE_IS_RESERVED", nullable = false)
    private Boolean houseIsReserved = false;
}