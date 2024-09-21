package com.emazon.cart.adapters.driving.rest.controller;

import com.emazon.cart.adapters.driving.rest.dto.request.ItemRequest;
import com.emazon.cart.adapters.driving.rest.dto.request.PageQuery;
import com.emazon.cart.adapters.driving.rest.dto.request.filter.ItemFilterRequest;
import com.emazon.cart.adapters.driving.rest.dto.response.CarResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.FullItemResponse;
import com.emazon.cart.adapters.driving.rest.dto.response.PageResponse;
import com.emazon.cart.adapters.driving.rest.service.CartService;
import com.emazon.cart.adapters.driving.rest.utils.RestConstants;
import com.emazon.cart.configuration.advice.responses.ExceptionResponse;
import com.emazon.cart.configuration.advice.responses.ValidationExceptionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts/")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

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
            @ApiResponse(
                    responseCode = RestConstants.CODE_BAD_REQUEST,
                    description = RestConstants.SWAGGER_VALIDATIONS_DONT_PASS,
                    content = @Content(schema = @Schema(implementation = ValidationExceptionResponse.class))),
    })
    @PostMapping("/{user-id}/items")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<CarResponse> addItem(
            @PathVariable("user-id") String userId,
            @RequestBody @Valid ItemRequest itemRequest) {
        return ResponseEntity.ok(cartService.addItem(userId, itemRequest));
    }

    @Operation(summary = RestConstants.SWAGGER_REMOVE_ITEM_SUMMARY)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = RestConstants.CODE_OK,
                    description = RestConstants.SWAGGER_REMOVE_ITEM_RESPONSE,
                    content = @Content(schema = @Schema(implementation = CarResponse.class))),
            @ApiResponse(
                    responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_REMOVE_ITEM_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(
                    responseCode = RestConstants.CODE_NOT_FOUND,
                    description = RestConstants.SWAGGER_CAR_NOT_FOUND,
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class))),
    })
    // Yes I know I can get userId from token, but I don't like to have jwtService in every microservice
    @DeleteMapping("/{user-id}/items/{product-id}")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<CarResponse> deleteItem(
            @PathVariable("user-id") String userId,
            @PathVariable("product-id") Long productId) {
        return ResponseEntity.ok(cartService.removeItem(userId, productId));

    }

    @GetMapping("/{user-id}/items")
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<PageResponse<FullItemResponse>> getCartItems(
            @PathVariable("user-id") String userId,
            @Nullable ItemFilterRequest filter,
            @Nullable PageQuery query){
        return ResponseEntity.ok(cartService.getItems(userId, filter, query));
    }

}
