package com.aksoyakin.pawtientcarebe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String gender;

    @Column(name = "mobile")
    private String phoneNumber;

    private String email;

    private String password;

    private String userType;

    private boolean isEnabled;

    @Transient
    private String specialization;
}
