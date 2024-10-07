package com.aksoyakin.pawtientcarebe.repository;

import com.aksoyakin.pawtientcarebe.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarianRepository extends JpaRepository<Veterinarian, Long> {
}
