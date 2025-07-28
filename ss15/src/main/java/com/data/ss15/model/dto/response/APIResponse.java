package com.data.ss15.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse<T>{
    private T data;
    private String message;
    private HttpStatus status;
    private boolean success;
}
