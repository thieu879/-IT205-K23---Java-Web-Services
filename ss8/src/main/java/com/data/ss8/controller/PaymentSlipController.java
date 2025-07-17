package com.data.ss8.controller;

import com.data.ss8.model.dto.request.PaymentSlipRequestDto;
import com.data.ss8.model.dto.response.ApiResponse;
import com.data.ss8.model.dto.response.PaymentSlipResponseDto;
import com.data.ss8.service.PaymentSlipService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/paymentslips")
public class PaymentSlipController {

    private final PaymentSlipService paymentSlipService;

    public PaymentSlipController(PaymentSlipService paymentSlipService) {
        this.paymentSlipService = paymentSlipService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentSlipResponseDto>> createPaymentSlip(
            @Valid @RequestBody PaymentSlipRequestDto paymentSlipRequestDto) {

        PaymentSlipResponseDto createdPaymentSlip = paymentSlipService.createPaymentSlip(paymentSlipRequestDto);

        ApiResponse<PaymentSlipResponseDto> response = new ApiResponse<>(
                "Thêm phiếu chi thành công",
                true,
                createdPaymentSlip
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentSlipResponseDto>> updatePaymentSlip(
            @PathVariable Long id,
            @Valid @RequestBody PaymentSlipRequestDto paymentSlipRequestDto) {

        PaymentSlipResponseDto updatedPaymentSlip = paymentSlipService.updatePaymentSlip(id, paymentSlipRequestDto);

        ApiResponse<PaymentSlipResponseDto> response = new ApiResponse<>(
                "Cập nhật phiếu chi thành công",
                true,
                updatedPaymentSlip
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePaymentSlip(@PathVariable Long id) {
        paymentSlipService.deletePaymentSlip(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "Xóa phiếu chi thành công",
                true,
                null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentSlipResponseDto>>> getAllPaymentSlips() {
        List<PaymentSlipResponseDto> paymentSlips = paymentSlipService.getAllPaymentSlips();

        ApiResponse<List<PaymentSlipResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách phiếu chi thành công",
                true,
                paymentSlips
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentSlipResponseDto>> getPaymentSlipById(@PathVariable Long id) {
        PaymentSlipResponseDto paymentSlip = paymentSlipService.getPaymentSlipById(id);

        ApiResponse<PaymentSlipResponseDto> response = new ApiResponse<>(
                "Lấy thông tin phiếu chi thành công",
                true,
                paymentSlip
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PaymentSlipResponseDto>>> getPaymentSlipsByTitle(
            @RequestParam String title) {
        List<PaymentSlipResponseDto> paymentSlips = paymentSlipService.getPaymentSlipsByTitle(title);

        ApiResponse<List<PaymentSlipResponseDto>> response = new ApiResponse<>(
                "Tìm kiếm phiếu chi thành công",
                true,
                paymentSlips
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/date-range")
    public ResponseEntity<ApiResponse<List<PaymentSlipResponseDto>>> getPaymentSlipsByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<PaymentSlipResponseDto> paymentSlips = paymentSlipService.getPaymentSlipsByDateRange(start, end);

        ApiResponse<List<PaymentSlipResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách phiếu chi theo khoảng thời gian thành công",
                true,
                paymentSlips
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/total-amount")
    public ResponseEntity<ApiResponse<Double>> getTotalAmountByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        Double totalAmount = paymentSlipService.getTotalAmountByDateRange(start, end);

        ApiResponse<Double> response = new ApiResponse<>(
                "Tính tổng tiền chi theo khoảng thời gian thành công",
                true,
                totalAmount
        );

        return ResponseEntity.ok(response);
    }
}
