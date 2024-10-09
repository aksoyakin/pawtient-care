package com.aksoyakin.pawtientcarebe.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

    private String message;

    private Object data;
}
