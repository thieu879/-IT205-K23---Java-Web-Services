package com.data.ss15.service;

import com.data.ss15.model.dto.request.ReviewDTO;
import com.data.ss15.model.dto.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    void createReview(ReviewDTO dto, Long userId);
    List<ReviewResponse> getReviewsByProductId(Long productId);
}
