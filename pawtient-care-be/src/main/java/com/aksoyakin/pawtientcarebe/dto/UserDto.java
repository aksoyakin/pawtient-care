package com.aksoyakin.pawtientcarebe.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    private String phoneNumber;

    private String email;

    private String userType;

    private boolean isEnabled;

    private String specialization;

    private LocalDate createdAt;

    private List<AppointmentDto> appointments;

    private List<ReviewDto> reviews;

    private long photoId;

    private byte[] photo;

    private double averageRating;

    private Long totalReviewers;
}
