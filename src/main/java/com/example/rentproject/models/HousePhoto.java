package com.example.rentproject.models;

import lombok.*;

import javax.persistence.*;

@Table(name = "house_photo", uniqueConstraints = {
        @UniqueConstraint(name = "HOUSE_PHOTO_UK", columnNames = "HOUSE_ID")
})
@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class HousePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HOUSE_PHOTO_ID", nullable = false)
    private Long id;

    @Column(name = "PHOTO_PATH", nullable = false, length = 128)
    private String photoPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOUSE_ID", nullable = false)
    private House house;
}