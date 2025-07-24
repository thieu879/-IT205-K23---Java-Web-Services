package com.data.ss13.service;

import com.data.ss13.entity.dto.request.CheckoutRequest;

public interface OrderService {
    void checkout(CheckoutRequest request);
}