package com.emazon.car.configuration.advice;

import com.emazon.car.configuration.advice.responses.ExceptionResponse;
import com.emazon.car.configuration.advice.responses.ValidationExceptionResponse;
import com.emazon.car.domain.exceptions.EntityNotFoundException;
import com.emazon.car.domain.exceptions.ItemAlreadyAddedException;
import com.emazon.car.domain.exceptions.MaxCategoryCountException;
import com.emazon.car.domain.exceptions.NotEnoughProductStockException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionAdvisor {

    private ExceptionResponse exceptionResponseBuilder(String message, HttpStatus status){
        return ExceptionResponse.builder()
                .statusCode(status.value())
                .status(status)
                .timestamp(LocalDateTime.now())
                .message(message).build();
    }

    public ResponseEntity<ExceptionResponse> handleGeneralException(RuntimeException e) {
        ExceptionResponse exceptionResponse = exceptionResponseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(RuntimeException e) {
        ExceptionResponse exceptionResponse = exceptionResponseBuilder(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(HttpMessageConversionException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageConversionException(HttpMessageConversionException e) {
        ExceptionResponse exceptionResponse = exceptionResponseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ExceptionResponse> handlePropertyReferenceException(PropertyReferenceException e) {
        ExceptionResponse exceptionResponse = exceptionResponseBuilder(e.getMessage(), HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(ItemAlreadyAddedException.class)
    public ResponseEntity<ExceptionResponse> handleItemAlreadyAddedException(ItemAlreadyAddedException e) {
        ExceptionResponse exceptionResponse = exceptionResponseBuilder(e.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(MaxCategoryCountException.class)
    public ResponseEntity<ExceptionResponse> handleMaxCategoryCountException(MaxCategoryCountException e) {
        ExceptionResponse exceptionResponse = exceptionResponseBuilder(e.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler(NotEnoughProductStockException.class)
    public ResponseEntity<ExceptionResponse> handleNotEnoughProductStockException(NotEnoughProductStockException e) {
        ExceptionResponse exceptionResponse = exceptionResponseBuilder(e.getMessage(), HttpStatus.CONFLICT);
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

    @ExceptionHandler( MethodArgumentNotValidException.class )
    public ResponseEntity<ValidationExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationExceptionResponse exceptionResponse = ValidationExceptionResponse.builder()
                .statusCode(e.getStatusCode().value())
                .status(HttpStatus.resolve(e.getStatusCode().value()))
                .timestamp(LocalDateTime.now())
                .errors(e.getFieldErrors().stream().map(field -> {
                    StringBuilder sb = new StringBuilder();
                    String rejectedValue = field.getRejectedValue() == null ? "null" : field.getRejectedValue().toString();
                    sb.append(field.getDefaultMessage()).append(": ").append(rejectedValue);
                    return sb.toString();
                }).toList())
                .message(e.getBody().getDetail()).build();
        return ResponseEntity.status(exceptionResponse.getStatusCode()).body(exceptionResponse);
    }

}

