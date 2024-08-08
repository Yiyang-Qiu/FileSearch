package com.FileSearch.web;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    protected <T> ResponseEntity<T> createResponse(T body, HttpStatus status) {
        return new ResponseEntity<>(body, status);
    }

    protected <T> ResponseEntity<T> successResponse(T body) {
        return createResponse(body, HttpStatus.OK);
    }

    protected ResponseEntity<String> errorResponse(String message, HttpStatus status) {
        return createResponse(message, status);
    }
}
