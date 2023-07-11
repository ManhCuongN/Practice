package com.example.demo.common;

import java.util.List;

public class ApiResponse {
    private int statusCode;
    private List<String> errors;
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponse(int statusCode, String message, List<String> errors) {
        this.statusCode = statusCode;
        this.errors = errors;
        this.message = message;
    }



}
