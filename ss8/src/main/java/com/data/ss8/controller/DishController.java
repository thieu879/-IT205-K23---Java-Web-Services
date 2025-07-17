package com.data.ss8.controller;

import com.data.ss8.model.dto.request.DishRequestDto;
import com.data.ss8.model.dto.response.ApiResponse;
import com.data.ss8.model.dto.response.DishResponseDto;
import com.data.ss8.service.DishService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    private final DishService dishService;

    public DishController(DishService dishService) {
        this.dishService = dishService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DishResponseDto>> createDish(
            @Valid @ModelAttribute DishRequestDto dishRequestDto) throws IOException {

        DishResponseDto createdDish = dishService.createDish(dishRequestDto);

        ApiResponse<DishResponseDto> response = new ApiResponse<>(
                "Thêm món ăn thành công",
                true,
                createdDish
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DishResponseDto>> updateDish(
            @PathVariable Long id,
            @Valid @ModelAttribute DishRequestDto dishRequestDto) throws IOException {

        DishResponseDto updatedDish = dishService.updateDish(id, dishRequestDto);

        ApiResponse<DishResponseDto> response = new ApiResponse<>(
                "Cập nhật món ăn thành công",
                true,
                updatedDish
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "Xóa món ăn thành công",
                true,
                null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DishResponseDto>>> getAllDishes() {
        List<DishResponseDto> dishes = dishService.getAllDishes();

        ApiResponse<List<DishResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách món ăn thành công",
                true,
                dishes
        );

        return ResponseEntity.ok(response);
    }
}
