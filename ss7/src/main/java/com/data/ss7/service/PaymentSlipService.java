package com.data.ss7.service;

import com.data.ss7.model.PaymentSlip;

import java.util.List;

public interface PaymentSlipService {
    List<PaymentSlip> getAllPaymentSlips();
    PaymentSlip getPaymentSlipById(Long id);
    PaymentSlip addPaymentSlip(PaymentSlip paymentSlip);
}
