package com.data.ss8.service;

import com.data.ss8.model.dto.request.IngredientRequestDto;
import com.data.ss8.model.dto.response.IngredientResponseDto;
import com.data.ss8.model.entity.Ingredient;
import com.data.ss8.repository.IngredientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final CloudinaryService cloudinaryService;

    public IngredientService(IngredientRepository ingredientRepository, CloudinaryService cloudinaryService) {
        this.ingredientRepository = ingredientRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public IngredientResponseDto createIngredient(IngredientRequestDto ingredientRequestDto) throws IOException {
        String imageUrl = null;

        if (ingredientRequestDto.getImage() != null && !ingredientRequestDto.getImage().isEmpty()) {
            imageUrl = cloudinaryService.uploadFile(ingredientRequestDto.getImage());
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setName(ingredientRequestDto.getName());
        ingredient.setStock(ingredientRequestDto.getStock());
        ingredient.setExpiry(ingredientRequestDto.getExpiry());
        ingredient.setImage(imageUrl);

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return convertToResponseDto(savedIngredient);
    }

    public IngredientResponseDto updateIngredient(Long id, IngredientRequestDto ingredientRequestDto) throws IOException {
        Ingredient existingIngredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy nguyên liệu với ID: " + id));

        existingIngredient.setName(ingredientRequestDto.getName());
        existingIngredient.setStock(ingredientRequestDto.getStock());
        existingIngredient.setExpiry(ingredientRequestDto.getExpiry());

        if (ingredientRequestDto.getImage() != null && !ingredientRequestDto.getImage().isEmpty()) {
            String newImageUrl = cloudinaryService.uploadFile(ingredientRequestDto.getImage());
            existingIngredient.setImage(newImageUrl);
        }

        Ingredient updatedIngredient = ingredientRepository.save(existingIngredient);

        return convertToResponseDto(updatedIngredient);
    }

    public void deleteIngredient(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new NoSuchElementException("Không tìm thấy nguyên liệu với ID: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    public List<IngredientResponseDto> getAllIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findAll();
        return ingredients.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public IngredientResponseDto getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy nguyên liệu với ID: " + id));
        return convertToResponseDto(ingredient);
    }

    private IngredientResponseDto convertToResponseDto(Ingredient ingredient) {
        IngredientResponseDto dto = new IngredientResponseDto();
        dto.setId(ingredient.getId());
        dto.setName(ingredient.getName());
        dto.setStock(ingredient.getStock());
        dto.setExpiry(ingredient.getExpiry());
        dto.setImage(ingredient.getImage());
        return dto;
    }
}

