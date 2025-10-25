package com.iam.service.exception;

import com.iam.service.mapper.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<com.iam.service.mapper.ErrorResponse> handlerUserNotFoundException(
            UserNotFoundException ex, HttpServletRequest request
    ){
        ErrorResponse errorResponse = new ErrorResponse(
                "USER_NOT_FOUND",
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
