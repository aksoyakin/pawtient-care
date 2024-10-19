package com.aksoyakin.pawtientcarebe.service.user.impl;

import com.aksoyakin.pawtientcarebe.dto.AppointmentDto;
import com.aksoyakin.pawtientcarebe.dto.ReviewDto;
import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.dto.converter.EntityConverter;
import com.aksoyakin.pawtientcarebe.dto.request.RegistrationRequest;
import com.aksoyakin.pawtientcarebe.dto.request.UserUpdateRequest;
import com.aksoyakin.pawtientcarebe.exception.ResourceNotFoundException;
import com.aksoyakin.pawtientcarebe.factory.UserFactory;
import com.aksoyakin.pawtientcarebe.model.Review;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.repository.UserRepository;
import com.aksoyakin.pawtientcarebe.service.appointment.AppointmentService;
import com.aksoyakin.pawtientcarebe.service.photo.PhotoService;
import com.aksoyakin.pawtientcarebe.service.review.ReviewService;
import com.aksoyakin.pawtientcarebe.service.user.UserService;
import com.aksoyakin.pawtientcarebe.utils.FeedBackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final EntityConverter<User, UserDto> entityConverter;
    private final AppointmentService appointmentService;
    private final PhotoService photoService;
    private final ReviewService reviewService;

    @Override
    public User register(RegistrationRequest registrationRequest) {
        return userFactory.createUser(registrationRequest);
    }

    @Override
    public User update(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = findById(userId);
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setGender(userUpdateRequest.getGender());
        user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setSpecialization(userUpdateRequest.getSpecialization());
        return userRepository.save(user);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(FeedBackMessage.RESOURCE_NOT_FOUND));
    }

    @Override
    public void delete(Long userId) {
        userRepository.findById(userId)
                .ifPresentOrElse(userRepository::delete, () -> {
                    throw new ResourceNotFoundException(FeedBackMessage.RESOURCE_NOT_FOUND);
                });
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users
                .stream()
                .map(user -> entityConverter.mapEntityToDto(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserWithDetails(Long userId) throws SQLException {
        User user = findById(userId);
        UserDto userDto = entityConverter.mapEntityToDto(user, UserDto.class);
        setUserAppointments(userDto);
        setUserPhoto(userDto, user);
        setUserReviews(userDto, userId);

        return userDto;
    }

    private void setUserAppointments(UserDto userDto) {
        List<AppointmentDto> appointments = appointmentService.getUserAppointments(userDto.getId());
        userDto.setAppointments(appointments);
    }

    private void setUserPhoto(UserDto userDto, User user) throws SQLException {
        if (user.getPhoto() != null) {
            userDto.setPhotoId(user.getPhoto().getId());
            userDto.setPhoto(photoService.getImageData(user.getPhoto().getId()));
        }
    }

    private void setUserReviews(UserDto userDto, Long userId) {
        Page<Review> reviewPage = reviewService.findAllReviewsByUserId(userId, 0, Integer.MAX_VALUE);
        List<ReviewDto> reviewsDto = reviewPage.getContent()
                .stream()
                .map(this::mapReviewToDto)
                .toList();

        if (!reviewsDto.isEmpty()) {
            double averageRating = reviewService.getAverageRatingForVeterinarian(userId);
        }
        userDto.setReviews(reviewsDto);
    }

    private ReviewDto mapReviewToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setStars(review.getStars());
        reviewDto.setFeedback(review.getFeedback());
        mapVeterinarianInfo(reviewDto, review);
        mapPatientInfo(reviewDto, review);
        return reviewDto;
    }

    private void mapVeterinarianInfo(ReviewDto reviewDto, Review review) {
        if (review.getVeterinarian() != null) {
            reviewDto.setVeterinarianId(review.getVeterinarian().getId());
            reviewDto.setVeterinarianName(review.getVeterinarian().getFirstName() + " " + review.getVeterinarian().getLastName());
            setReviewerPhoto(reviewDto, review);
        }
    }

    private void mapPatientInfo(ReviewDto reviewDto, Review review) {
        if (review.getPatient() != null) {
            reviewDto.setPatientId(review.getPatient().getId());
            reviewDto.setPatientName(review.getPatient().getFirstName() + " " + review.getPatient().getLastName());
            setVeterinarianPhoto(reviewDto, review);
        }
    }

    private void setReviewerPhoto(ReviewDto reviewDto, Review review) {
        if (review.getPatient().getPhoto() != null) {
            try {
                reviewDto.setPatientImage(photoService.getImageData(review.getPatient().getPhoto().getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            reviewDto.setPatientImage(null);
        }
    }

    private void setVeterinarianPhoto(ReviewDto reviewDto, Review review) {
        if (review.getVeterinarian().getPhoto() != null) {
            try {
                reviewDto.setVeterinarianImage(photoService.getImageData(review.getVeterinarian().getPhoto().getId()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            reviewDto.setVeterinarianImage(null);
        }
    }
}
