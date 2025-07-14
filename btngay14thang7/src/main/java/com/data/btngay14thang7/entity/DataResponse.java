package com.data.btngay14thang7.entity;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class DataResponse<T> {
    private T data;
    private HttpStatus status;
    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public DataResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public DataResponse(T data, HttpStatus status) {
        this.data = data;
        this.status = status;
        this.message = status.getReasonPhrase();
        this.timestamp = LocalDateTime.now();
    }

    public DataResponse(T data, HttpStatus status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
