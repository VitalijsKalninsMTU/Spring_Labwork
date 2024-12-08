package com.example.labs.services;

import com.example.labs.entities.Pet;
import com.example.labs.exceptions.NotFoundException;
import com.example.labs.dtos.PetRecord;
import com.example.labs.dtos.PetStats;

import java.util.List;

public interface PetServiceInterface {
    Pet createPet(Pet pet);
    List<Pet> getAllPets();
    Pet getPetById(Integer id) throws NotFoundException;
    Pet updatePetById(Integer id, Pet updated) throws NotFoundException;
    void deletePetById(Integer id) throws NotFoundException;
    int deleteAllPetsByName(String name);
    List<Pet> getPetsByAnimalType(String animalType);
    List<Pet> getPetsByBreed(String breed);
    List<PetRecord> getAllPetRecords();
    PetStats getPetStats();
}
