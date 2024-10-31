package com.aksoyakin.pawtientcarebe.controller;

import com.aksoyakin.pawtientcarebe.dto.ReviewDto;
import com.aksoyakin.pawtientcarebe.dto.request.ReviewUpdateRequest;
import com.aksoyakin.pawtientcarebe.dto.response.ApiResponse;
import com.aksoyakin.pawtientcarebe.exception.AlreadyExists;
import com.aksoyakin.pawtientcarebe.exception.ResourceNotFoundException;
import com.aksoyakin.pawtientcarebe.model.Review;
import com.aksoyakin.pawtientcarebe.service.review.ReviewService;
import com.aksoyakin.pawtientcarebe.utils.FeedBackMessage;
import com.aksoyakin.pawtientcarebe.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping(UrlMapping.REVIEWS)
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ModelMapper modelMapper;
    ;

    @PostMapping(UrlMapping.SUBMIT_REVIEW)
    public ResponseEntity<ApiResponse> saveReview(@RequestBody Review review,
                                                  @RequestParam Long reviewerId,
                                                  @RequestParam Long veterinarianId) {
        try {
            Review savedReview = reviewService.saveReview(review, reviewerId, veterinarianId);
            return ResponseEntity
                    .ok(new ApiResponse(FeedBackMessage.CREATE_SUCCESS, savedReview.getId())); //TODO: id will change
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity
                    .status(NOT_ACCEPTABLE)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (AlreadyExists e) {
            return ResponseEntity
                    .status(CONFLICT)
                    .body(new ApiResponse(e.getMessage(), null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_USER_REVIEWS)
    public ResponseEntity<ApiResponse> getReviewsByUserId(@PathVariable Long userId,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Page<Review> reviewPage = reviewService.findAllReviewsByUserId(userId, page, size);
        Page<ReviewDto> reviewsDto = reviewPage.map((review) -> modelMapper.map(review, ReviewDto.class));
        return ResponseEntity
                .status(FOUND)
                .body(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, reviewsDto));

    }

    @PutMapping(UrlMapping.UPDATE_REVIEW)
    public ResponseEntity<ApiResponse> updateReview(@RequestBody ReviewUpdateRequest updateRequest,
                                                    @PathVariable Long reviewId) {
        try {
            Review updatedReview = reviewService.updateReview(reviewId, updateRequest);
            return ResponseEntity
                    .ok(new ApiResponse(FeedBackMessage.UPDATE_SUCCESS, updatedReview.getId())); //TODO: id will change
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping(UrlMapping.DELETE_REVIEW)
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable Long reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            return ResponseEntity
                    .ok(new ApiResponse(FeedBackMessage.DELETE_SUCCESS, null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping(UrlMapping.GET_AVERAGE_RATING)
    public ResponseEntity<ApiResponse> getAverageRatingForVeterinarian(@PathVariable Long veterinarianId) {
        double averageRating = reviewService.getAverageRatingForVeterinarian(veterinarianId);
        return ResponseEntity
                .ok(new ApiResponse(FeedBackMessage.RESOURCE_FOUND, averageRating));
    }
}
