package com.aksoyakin.pawtientcarebe.service.appointment;

import com.aksoyakin.pawtientcarebe.dto.request.AppointmentUpdateRequest;
import com.aksoyakin.pawtientcarebe.model.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment, Long senderId, Long recipientId);

    List<Appointment> getAllAppointments();

    Appointment updateAppointment(Long id, AppointmentUpdateRequest request);

    void deleteAppointment(Long id);

    Appointment getAppointmentById(Long id);

    Appointment getAppointmentByNo(String appointmentNo);
}
