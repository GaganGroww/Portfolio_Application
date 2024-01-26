package com.example.groww.Portfolio_Application.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ServiceResponse<T> {
    private T result;
    private String errorMessage;
}

