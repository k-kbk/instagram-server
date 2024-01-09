package com.group.instagramserver.common.api

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<ApiResponse<Unit>> {
        return ResponseEntity(ApiResponse.error(exception.message), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleIMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ApiResponse<Unit>> {
        return ResponseEntity(ApiResponse.error(exception.message), HttpStatus.BAD_REQUEST)
    }
}
