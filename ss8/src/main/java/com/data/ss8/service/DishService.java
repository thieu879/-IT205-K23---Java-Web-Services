package com.data.ss8.service;

import com.data.ss8.model.dto.request.DishRequestDto;
import com.data.ss8.model.dto.response.DishResponseDto;
import com.data.ss8.model.entity.Dish;
import com.data.ss8.repository.DishRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class DishService {
    private final DishRepository dishRepository;
    private final CloudinaryService cloudinaryService;

    public DishService(DishRepository dishRepository, CloudinaryService cloudinaryService) {
        this.dishRepository = dishRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public DishResponseDto createDish(DishRequestDto dishRequestDto) throws IOException {
        String imageUrl = null;

        if (dishRequestDto.getImage() != null && !dishRequestDto.getImage().isEmpty()) {
            imageUrl = cloudinaryService.uploadFile(dishRequestDto.getImage());
        }

        // Chuyển đổi DTO thành Entity
        Dish dish = new Dish();
        dish.setName(dishRequestDto.getName());
        dish.setDescription(dishRequestDto.getDescription());
        dish.setPrice(dishRequestDto.getPrice());
        dish.setStatus(dishRequestDto.getStatus());
        dish.setImage(imageUrl);

        Dish savedDish = dishRepository.save(dish);

        return convertToResponseDto(savedDish);
    }

    public DishResponseDto updateDish(Long id, DishRequestDto dishRequestDto) throws IOException {
        Dish existingDish = dishRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Không tìm thấy món ăn với ID: " + id));

        existingDish.setName(dishRequestDto.getName());
        existingDish.setDescription(dishRequestDto.getDescription());
        existingDish.setPrice(dishRequestDto.getPrice());
        existingDish.setStatus(dishRequestDto.getStatus());

        if (dishRequestDto.getImage() != null && !dishRequestDto.getImage().isEmpty()) {
            String newImageUrl = cloudinaryService.uploadFile(dishRequestDto.getImage());
            existingDish.setImage(newImageUrl);
        }

        Dish updatedDish = dishRepository.save(existingDish);

        return convertToResponseDto(updatedDish);
    }

    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new NoSuchElementException("Không tìm thấy món ăn với ID: " + id);
        }
        dishRepository.deleteById(id);
    }

    public List<DishResponseDto> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        return dishes.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private DishResponseDto convertToResponseDto(Dish dish) {
        DishResponseDto dto = new DishResponseDto();
        dto.setId(dish.getId());
        dto.setName(dish.getName());
        dto.setDescription(dish.getDescription());
        dto.setPrice(dish.getPrice());
        dto.setStatus(dish.getStatus());
        dto.setImage(dish.getImage());
        return dto;
    }
}
