package com.example.labs.repositories;

import com.example.labs.entities.Pet;
import com.example.labs.dtos.PetRecord;
import com.example.labs.dtos.PetStats;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface PetRepository extends JpaRepository<Pet, Integer>{
    @Modifying
    int deleteByNameIgnoreCase(String name);
    List<Pet> findByAnimalType(String animalType);
    List<Pet> findByBreedOrderByAge(String breed);
    @Query("SELECT new com.example.labs.dtos.PetRecord(p.name, p.animalType, p.breed) FROM Pet p")
    List<PetRecord> findAllRecords();
    @Query("SELECT new com.example.labs.dtos.PetStats(AVG(p.age), MAX(p.age), COUNT(p)) FROM Pet p")
    PetStats getPetStats();
}