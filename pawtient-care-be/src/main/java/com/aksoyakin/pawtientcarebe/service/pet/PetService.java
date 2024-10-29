package com.aksoyakin.pawtientcarebe.service.pet;

import com.aksoyakin.pawtientcarebe.model.Pet;

import java.util.List;

public interface PetService {

    List<Pet> savePetForAppointment(List<Pet> pets);

    Pet updatePet(Pet pet, Long petId);

    void deletePet(Long petId);

    Pet getPetById(Long petId);

    List<String> getPetTypes();

    List<String> getPetColors();

    List<String> getPetBreeds(String petType);
}
