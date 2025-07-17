package com.data.ss8.service;

import com.data.ss8.model.dto.request.PaymentSlipRequestDto;
import com.data.ss8.model.dto.response.PaymentSlipResponseDto;
import com.data.ss8.model.entity.PaymentSlip;
import com.data.ss8.repository.PaymentSlipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentSlipService {

    private final PaymentSlipRepository paymentSlipRepository;

    public PaymentSlipService(PaymentSlipRepository paymentSlipRepository) {
        this.paymentSlipRepository = paymentSlipRepository;
    }

    public PaymentSlipResponseDto createPaymentSlip(PaymentSlipRequestDto paymentSlipRequestDto) {
        PaymentSlip paymentSlip = new PaymentSlip();
        paymentSlip.setTitle(paymentSlipRequestDto.getTitle());
        paymentSlip.setDescription(paymentSlipRequestDto.getDescription());
        paymentSlip.setMoney(paymentSlipRequestDto.getMoney());

        PaymentSlip savedPaymentSlip = paymentSlipRepository.save(paymentSlip);

        return convertToResponseDto(savedPaymentSlip);
    }

    public PaymentSlipResponseDto updatePaymentSlip(Long id, PaymentSlipRequestDto paymentSlipRequestDto) {
        PaymentSlip existingPaymentSlip = paymentSlipRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy phiếu chi với ID: " + id));

        existingPaymentSlip.setTitle(paymentSlipRequestDto.getTitle());
        existingPaymentSlip.setDescription(paymentSlipRequestDto.getDescription());
        existingPaymentSlip.setMoney(paymentSlipRequestDto.getMoney());

        PaymentSlip updatedPaymentSlip = paymentSlipRepository.save(existingPaymentSlip);

        return convertToResponseDto(updatedPaymentSlip);
    }

    public void deletePaymentSlip(Long id) {
        if (!paymentSlipRepository.existsById(id)) {
            throw new NoSuchElementException("Không tìm thấy phiếu chi với ID: " + id);
        }
        paymentSlipRepository.deleteById(id);
    }

    public List<PaymentSlipResponseDto> getAllPaymentSlips() {
        List<PaymentSlip> paymentSlips = paymentSlipRepository.findAllByOrderByCreatedAtDesc();
        return paymentSlips.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public PaymentSlipResponseDto getPaymentSlipById(Long id) {
        PaymentSlip paymentSlip = paymentSlipRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy phiếu chi với ID: " + id));
        return convertToResponseDto(paymentSlip);
    }

    public List<PaymentSlipResponseDto> getPaymentSlipsByTitle(String title) {
        List<PaymentSlip> paymentSlips = paymentSlipRepository.findByTitleContainingIgnoreCase(title);
        return paymentSlips.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public List<PaymentSlipResponseDto> getPaymentSlipsByDateRange(LocalDateTime start, LocalDateTime end) {
        List<PaymentSlip> paymentSlips = paymentSlipRepository.findByCreatedAtBetween(start, end);
        return paymentSlips.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public Double getTotalAmountByDateRange(LocalDateTime start, LocalDateTime end) {
        Double total = paymentSlipRepository.getTotalAmountByDateRange(start, end);
        return total != null ? total : 0.0;
    }

    private PaymentSlipResponseDto convertToResponseDto(PaymentSlip paymentSlip) {
        PaymentSlipResponseDto dto = new PaymentSlipResponseDto();
        dto.setId(paymentSlip.getId());
        dto.setTitle(paymentSlip.getTitle());
        dto.setDescription(paymentSlip.getDescription());
        dto.setMoney(paymentSlip.getMoney());
        dto.setCreatedAt(paymentSlip.getCreatedAt());
        return dto;
    }
}
