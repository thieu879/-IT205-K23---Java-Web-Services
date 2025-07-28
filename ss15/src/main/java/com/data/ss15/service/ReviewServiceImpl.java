package com.data.ss15.service;

import com.data.ss15.model.dto.request.ReviewDTO;
import com.data.ss15.model.dto.response.ReviewResponse;
import com.data.ss15.model.entity.Product;
import com.data.ss15.model.entity.Review;
import com.data.ss15.model.entity.User;
import com.data.ss15.repository.OrderRepo;
import com.data.ss15.repository.ProductRepo;
import com.data.ss15.repository.ReviewRepo;
import com.data.ss15.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;

    @Override
    public void createReview(ReviewDTO dto, Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User không tồn tại"));

        Product product = productRepo.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Sản phẩm không tồn tại"));

        if (reviewRepo.existsByUserIdAndProductId(userId, dto.getProductId())) {
            throw new RuntimeException("Bạn đã đánh giá sản phẩm này rồi");
        }

        boolean hasPurchased = orderRepo.existsByUserIdAndProductId(userId, dto.getProductId());
        if (!hasPurchased) {
            throw new RuntimeException("Bạn chưa từng mua sản phẩm này");
        }

        Review review = Review.builder()
                .user(user)
                .product(product)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .createdDate(LocalDateTime.now())
                .build();
        reviewRepo.save(review);
    }

    @Override
    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        List<Review> reviews = reviewRepo.findByProductId(productId);
        return reviews.stream()
                .map(r -> ReviewResponse.builder()
                        .username(r.getUser().getEmail())
                        .rating(r.getRating())
                        .comment(r.getComment())
                        .createdDate(r.getCreatedDate())
                        .build())
                .toList();
    }
}
