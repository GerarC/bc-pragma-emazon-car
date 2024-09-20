package com.emazon.cart.adapters.driven.jpa.entity;

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
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "car_id")
    private String id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "car", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ItemEntity> items;
}
