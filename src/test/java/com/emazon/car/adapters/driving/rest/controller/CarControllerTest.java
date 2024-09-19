package com.emazon.car.adapters.driving.rest.controller;

import com.emazon.car.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.car.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.car.adapters.driving.rest.dto.response.ItemResponse;
import com.emazon.car.adapters.driving.rest.mapper.request.ItemRequestMapper;
import com.emazon.car.adapters.driving.rest.mapper.response.CarResponseMapper;
import com.emazon.car.adapters.driving.rest.service.CarService;
import com.emazon.car.adapters.driving.rest.utils.JsonParser;
import com.emazon.car.domain.api.CarServicePort;
import com.emazon.car.domain.model.Car;
import com.emazon.car.domain.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CarController.class)
@AutoConfigureMockMvc(addFilters = false)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CarService carService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItem() throws Exception {
        String userId = "userId";
        ItemRequest itemRequest = new ItemRequest(5L, 10);
        ItemResponse itemResponse = new ItemResponse(5L, 5L, 10);
        CarResponse carResponse = new CarResponse(userId, LocalDateTime.now(), LocalDateTime.now(), List.of(itemResponse));
        when(carService.addItem(any(), any())).thenReturn(carResponse);
        this.mockMvc.perform(post("/cars/{user-id}/items", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonParser.toJson(itemRequest)))
                .andExpect(status().isOk());
    }
}