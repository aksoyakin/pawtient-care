package com.aksoyakin.pawtientcarebe.repository;

import com.aksoyakin.pawtientcarebe.model.Appointment;
import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.model.Veterinarian;
import com.aksoyakin.pawtientcarebe.model.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByAppointmentNo(String appointmentNo);

    boolean existsByVeterinarianIdAndPatientIdAndStatus(Long veterinarianId, Long reviewerId, AppointmentStatus appointmentStatus);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id =:userId OR a.veterinarian.id =:userId")
    List<Appointment> findAllByUserId(@Param("userId") Long userId);

    List<Appointment> findByVeterinarianAndAppointmentDate(User veterinarian, LocalDate requestedDate);
}
