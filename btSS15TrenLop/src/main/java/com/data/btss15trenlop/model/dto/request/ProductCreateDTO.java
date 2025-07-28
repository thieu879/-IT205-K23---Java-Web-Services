package com.data.btss15trenlop.model.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {
    @NotBlank(message = "Tên sản phẩm không được trống")
    private String productName;

    private String producer;

    @Min(value = 1900, message = "Năm sản xuất không hợp lệ")
    private int yearMaking;

    @Future(message = "Ngày hết hạn phải là ngày trong tương lai")
    private LocalDate expireDate;

    @Min(value = 0, message = "Số lượng phải >= 0")
    private int quantity;

    @DecimalMin(value = "0.0", message = "Giá phải >= 0")
    private double price;

    @NotNull(message = "Category ID không được trống")
    private Long categoryId;
}
