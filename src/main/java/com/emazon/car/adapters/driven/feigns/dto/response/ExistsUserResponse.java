package com.emazon.car.adapters.driven.feigns.dto.response;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExistsUserResponse {
    private boolean exists;
}
