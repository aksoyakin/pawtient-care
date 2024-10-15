package com.aksoyakin.pawtientcarebe.repository;

import com.aksoyakin.pawtientcarebe.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
