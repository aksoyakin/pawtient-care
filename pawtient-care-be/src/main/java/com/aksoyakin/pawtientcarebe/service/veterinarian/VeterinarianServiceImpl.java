package com.aksoyakin.pawtientcarebe.service.veterinarian;

import com.aksoyakin.pawtientcarebe.dto.UserDto;
import com.aksoyakin.pawtientcarebe.dto.converter.EntityConverter;
import com.aksoyakin.pawtientcarebe.model.Veterinarian;
import com.aksoyakin.pawtientcarebe.repository.ReviewRepository;
import com.aksoyakin.pawtientcarebe.repository.UserRepository;
import com.aksoyakin.pawtientcarebe.repository.VeterinarianRepository;
import com.aksoyakin.pawtientcarebe.service.photo.PhotoService;
import com.aksoyakin.pawtientcarebe.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarianServiceImpl implements VeterinarianService {

    private final VeterinarianRepository veterinarianRepository;
    private final EntityConverter<Veterinarian, UserDto> entityConverter;

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;

    private final PhotoService photoService;

    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllVeterinariansWithDetails() {
        List<Veterinarian> veterinarians = userRepository.findAllByUserType("VET");
        return veterinarians.stream()
                .map(this :: mapVeterinarianToUserDto)
                .toList();
    }

    private UserDto mapVeterinarianToUserDto(Veterinarian veterinarian) {
        UserDto userDto = entityConverter.mapEntityToDto(veterinarian, UserDto.class);

        double averageRating = reviewService.getAverageRatingForVeterinarian(veterinarian.getId());
        Long totalReviewer = reviewRepository.countByVeterinarianId(veterinarian.getId());
        userDto.setAverageRating(averageRating);
        userDto.setTotalReviewers(totalReviewer);

        if(veterinarian.getPhoto() != null) {
            try {
                byte[] photoBytes = photoService.getImageData(veterinarian.getId());
                userDto.setPhoto(photoBytes);
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return userDto;
    }
}
