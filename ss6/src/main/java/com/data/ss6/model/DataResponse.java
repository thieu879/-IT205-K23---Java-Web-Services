package com.data.ss6.model;

import lombok.*;
import org.springframework.http.HttpStatus;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DataResponse<T> {
    private T data;
    private HttpStatus status;
}
