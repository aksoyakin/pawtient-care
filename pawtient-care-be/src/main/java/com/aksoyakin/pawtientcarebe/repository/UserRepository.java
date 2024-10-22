package com.aksoyakin.pawtientcarebe.repository;

import com.aksoyakin.pawtientcarebe.model.User;
import com.aksoyakin.pawtientcarebe.model.Veterinarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    List<Veterinarian> findAllByUserType(String vet);
}
