package com.emazon.car.adapters.driving.rest.controller;

import com.emazon.car.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.car.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.car.adapters.driving.rest.service.CarService;
import com.emazon.car.adapters.driving.rest.utils.RestConstants;
import com.emazon.car.configuration.advice.responses.ExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars/")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @Operation(summary = RestConstants.SWAGGER_ADD_ITEM_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_ADD_ITEM_RESPONSE,
                    content = @Content(schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(
                    responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_PRODUCT_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(
                    responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_USER_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    @PostMapping("/{user-id}/items") // I use this way because I like to separate items by user
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<CarResponse> addItem(
            @PathVariable("user-id") String userId,
            @RequestBody @Valid ItemRequest itemRequest) {
        return ResponseEntity.ok(carService.addItem(userId, itemRequest));
    }
}
