package com.example.labs.repositories;

import com.example.labs.entities.Household;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HouseholdRepository extends JpaRepository<Household, String>{
    @EntityGraph(attributePaths = {"pets"})
    Optional<Household> findHouseholdWithPetsByEircode(String eircode);

    @Query("SELECT h FROM Household h LEFT JOIN h.pets p WHERE p IS NULL")
    List<Household> findHouseholdsWithNoPets();
}
