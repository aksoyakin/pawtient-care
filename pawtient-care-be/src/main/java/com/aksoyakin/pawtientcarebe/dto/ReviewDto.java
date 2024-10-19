package com.aksoyakin.pawtientcarebe.dto;

import lombok.Data;

@Data
public class ReviewDto {

    private Long id;

    private String feedback;

    private int stars;

    private Long veterinarianId;

    private String veterinarianName;

    private Long patientId;

    private String patientName;

    private byte[] patientImage;

    private byte[] veterinarianImage;
}
