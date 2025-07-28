package com.data.btss15trenlop.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    private String producer;
    private int yearMaking;
    private LocalDate expireDate;

    @Builder.Default
    private int quantity = 0;

    @Builder.Default
    private double price = 0.0;

    @Builder.Default
    private boolean status = true;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "cate_id")
    private Category category;
}