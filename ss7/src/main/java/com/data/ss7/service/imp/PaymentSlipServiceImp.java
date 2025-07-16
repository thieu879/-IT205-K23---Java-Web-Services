package com.data.ss7.service.imp;

import com.data.ss7.model.PaymentSlip;
import com.data.ss7.repository.PaymentSlipRepository;
import com.data.ss7.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PaymentSlipServiceImp implements PaymentSlipService {

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    @Override
    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }

    @Override
    public PaymentSlip getPaymentSlipById(Long id) {
        return paymentSlipRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy phiếu chi có id: " + id));
    }

    @Override
    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        return paymentSlipRepository.save(paymentSlip);
    }
}
