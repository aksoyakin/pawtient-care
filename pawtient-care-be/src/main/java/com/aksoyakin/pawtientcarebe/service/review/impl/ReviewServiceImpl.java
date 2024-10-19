package com.aksoyakin.pawtientcarebe.service.review.impl;

import com.aksoyakin.pawtientcarebe.dto.request.ReviewUpdateRequest;
import com.aksoyakin.pawtientcarebe.exception.ResourceNotFoundException;
import com.aksoyakin.pawtientcarebe.model.Review;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.repository.AppointmentRepository;
import com.aksoyakin.pawtientcarebe.repository.ReviewRepository;
import com.aksoyakin.pawtientcarebe.repository.UserRepository;
import com.aksoyakin.pawtientcarebe.service.review.ReviewService;
import com.aksoyakin.pawtientcarebe.utils.FeedBackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository; // TODO: change this with the service
    private final UserRepository userRepository;    // TODO: change this with the service

    // TODO: Refactor this method!
    @Override
    public Review saveReview(Review review, Long reviewerId, Long veterinarianId) {
        if (veterinarianId.equals(reviewerId)) {
            throw new IllegalArgumentException(FeedBackMessage.NOT_ALLOWED);
        }
/*
        Optional<Review> existingReview = reviewRepository
                .findByVeterinarianIdAndPatientId(veterinarianId, reviewerId);
        if (existingReview.isPresent()) {
            throw new AlreadyExists(FeedBackMessage.ALREADY_REVIEWED);
        }

        boolean hadCompletedAppointments =
                appointmentRepository.existsByVeterinarianIdAndPatientIdAndStatus(veterinarianId, reviewerId, AppointmentStatus.COMPLETED);
        if (!hadCompletedAppointments) {
            throw new IllegalStateException(FeedBackMessage.APPLICATION_NOT_COMPLETED);
        }
*/
        User vet = userRepository.findById(veterinarianId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(FeedBackMessage.VET_OR_PATIENT_NOT_FOUND));
        User user = userRepository.findById(reviewerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(FeedBackMessage.VET_OR_PATIENT_NOT_FOUND));

        review.setVeterinarian(vet);
        review.setPatient(user);

        return reviewRepository.save(review);
    }

    @Transactional
    @Override
    public double getAverageRatingForVeterinarian(Long veterinarianId) {
        List<Review> reviews = reviewRepository.findByVeterinarianId(veterinarianId);
        return reviews.isEmpty() ? 0 : reviews.stream()
                .mapToInt(Review::getStars)
                .average()
                .orElse(0.0);
    }

    @Override
    public Review updateReview(Long reviewerId, ReviewUpdateRequest review) {
        return reviewRepository.findById(reviewerId)
                .map(existingReview -> {
                    existingReview.setStars(review.getStars());
                    existingReview.setFeedback(review.getFeedback());
                    return reviewRepository.save(existingReview);
                })
                .orElseThrow(() -> new ResourceNotFoundException(FeedBackMessage.RESOURCE_NOT_FOUND));
    }

    @Override
    public Page<Review> findAllReviewsByUserId(Long userId, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return reviewRepository.findAllByUserId(userId, pageRequest);
    }

    @Override
    public void deleteReview(Long reviewerId) {
        reviewRepository.findById(reviewerId)
                .ifPresentOrElse(Review::removeRelationship, () -> {
                    throw new ResourceNotFoundException(FeedBackMessage.RESOURCE_NOT_FOUND);
                });
        reviewRepository.deleteById(reviewerId);
    }
}
