package com.aksoyakin.pawtientcarebe.dto.request;

import com.aksoyakin.pawtientcarebe.model.Appointment;
import com.aksoyakin.pawtientcarebe.model.Pet;
import lombok.Data;

import java.util.List;

@Data
public class BookAppointmentRequest {

    private Appointment appointment;
    private List<Pet> pets;
    
}
