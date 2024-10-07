package com.aksoyakin.pawtientcarebe.repository;

import com.aksoyakin.pawtientcarebe.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
