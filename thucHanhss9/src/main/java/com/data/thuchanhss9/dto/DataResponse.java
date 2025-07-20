package com.data.thuchanhss9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataResponse<T> {
    private T data;
    private HttpStatus status;
}

