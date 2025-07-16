package com.data.ss7.controller;

import com.data.ss7.model.PaymentSlip;
import com.data.ss7.model.dto.DataResponse;
import com.data.ss7.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/paymentslips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;

    @GetMapping
    public ResponseEntity<DataResponse<List<PaymentSlip>>> getPaymentSlips() {
        List<PaymentSlip> paymentSlips = paymentSlipService.getAllPaymentSlips();
        return new ResponseEntity<>(new DataResponse<>(paymentSlips, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<PaymentSlip>> addPaymentSlip(@Valid @RequestBody PaymentSlip paymentSlip) {
        PaymentSlip savedPaymentSlip = paymentSlipService.addPaymentSlip(paymentSlip);
        return new ResponseEntity<>(new DataResponse<>(savedPaymentSlip, HttpStatus.CREATED), HttpStatus.CREATED);
    }
}
