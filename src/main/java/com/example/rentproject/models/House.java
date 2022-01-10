package com.example.rentproject.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "HOUSE_NAME", nullable = false, length = 128)
    private String houseName;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "TYPE", nullable = false, length = 36)
    private String type;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "ROOMS", nullable = false, length = 128)
    private String rooms;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "GUESTS", nullable = false, length = 128)
    private String guests;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "PRICE", nullable = false, length = 128)
    private String price;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "LOCATION", nullable = false, length = 300)
    private String location;

    @NotBlank(message = "Поле не может быть пустым")
    @Column(name = "DESCRIPTION", nullable = false, length = 300)
    private String description;

    @Column(name = "HOUSE_IS_RESERVED", nullable = false)
    private Boolean houseIsReserved = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;
}