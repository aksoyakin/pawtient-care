package com.aksoyakin.pawtientcarebe.service.review;

import com.aksoyakin.pawtientcarebe.dto.request.ReviewUpdateRequest;
import com.aksoyakin.pawtientcarebe.model.Review;
import org.springframework.data.domain.Page;

public interface ReviewService {

    Review saveReview(Review review, Long reviewerId, Long veterinarianId);

    double getAverageRatingForVeterinarian(Long veterinarianId);

    Review updateReview(Long reviewerId, ReviewUpdateRequest review);

    Page<Review> findAllReviewsByUserId(Long userId, int page, int size);

    void deleteReview(Long reviewerId);
}
