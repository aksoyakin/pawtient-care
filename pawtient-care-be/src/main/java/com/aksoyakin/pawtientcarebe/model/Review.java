package com.aksoyakin.pawtientcarebe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String feedback;

    private int stars;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id")
    private User veterinarian;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private User patient;

    public void removeRelationship() {
        Optional.ofNullable(veterinarian)
                .ifPresent(vet -> vet.getReviews().remove(this));
        Optional.ofNullable(patient)
                .ifPresent(pat -> pat.getReviews().remove(this));
    }
}
