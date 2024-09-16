package com.emazon.car.adapters.driven.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "car_id")
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "modified", nullable = false)
    private LocalDateTime modified;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemEntity> items;
}
