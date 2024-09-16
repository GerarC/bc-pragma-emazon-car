package com.emazon.car.adapters.driving.rest.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private String userId;
    private LocalDateTime modified;
    private List<ItemResponse> items;
}
