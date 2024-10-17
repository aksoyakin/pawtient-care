package com.aksoyakin.pawtientcarebe.dto.request;

import lombok.Data;

@Data
public class ReviewUpdateRequest {

    private int stars;

    private String feedback;
}
