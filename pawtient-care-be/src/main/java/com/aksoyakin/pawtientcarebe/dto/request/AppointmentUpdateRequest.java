package com.aksoyakin.pawtientcarebe.dto.request;

import lombok.Data;

@Data
public class AppointmentUpdateRequest {

    private String appointmentDate;

    private String appointmentTime;

    private String reason;
}

