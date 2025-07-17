package com.data.ss8.controller;

import com.data.ss8.model.dto.request.IngredientRequestDto;
import com.data.ss8.model.dto.response.ApiResponse;
import com.data.ss8.model.dto.response.IngredientResponseDto;
import com.data.ss8.service.IngredientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ingredients")
public class IngredientController {

    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IngredientResponseDto>> createIngredient(
            @Valid @ModelAttribute IngredientRequestDto ingredientRequestDto) throws IOException {

        IngredientResponseDto createdIngredient = ingredientService.createIngredient(ingredientRequestDto);

        ApiResponse<IngredientResponseDto> response = new ApiResponse<>(
                "Thêm nguyên liệu thành công",
                true,
                createdIngredient
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientResponseDto>> updateIngredient(
            @PathVariable Long id,
            @Valid @ModelAttribute IngredientRequestDto ingredientRequestDto) throws IOException {

        IngredientResponseDto updatedIngredient = ingredientService.updateIngredient(id, ingredientRequestDto);

        ApiResponse<IngredientResponseDto> response = new ApiResponse<>(
                "Cập nhật nguyên liệu thành công",
                true,
                updatedIngredient
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);

        ApiResponse<Void> response = new ApiResponse<>(
                "Xóa nguyên liệu thành công",
                true,
                null
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IngredientResponseDto>>> getAllIngredients() {
        List<IngredientResponseDto> ingredients = ingredientService.getAllIngredients();

        ApiResponse<List<IngredientResponseDto>> response = new ApiResponse<>(
                "Lấy danh sách nguyên liệu thành công",
                true,
                ingredients
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientResponseDto>> getIngredientById(@PathVariable Long id) {
        IngredientResponseDto ingredient = ingredientService.getIngredientById(id);

        ApiResponse<IngredientResponseDto> response = new ApiResponse<>(
                "Lấy thông tin nguyên liệu thành công",
                true,
                ingredient
        );

        return ResponseEntity.ok(response);
    }
}
