package com.example.labs.services;

import com.example.labs.entities.Household;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.labs.exceptions.NotFoundException;
import com.example.labs.repositories.HouseholdRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HouseholdService implements HouseholdServiceInterface{
    private final HouseholdRepository householdRepo;

    @Override
    public Household getHouseholdByEircode(String eircode) throws NotFoundException{
        return householdRepo.findById(eircode)
            .orElseThrow(() -> new NotFoundException(String.format("Household with eircode: %s not found.", eircode)));
    }

    @Override
    public Household getHouseholdWithPetsByEircode(String eircode) throws NotFoundException{
        return householdRepo.findHouseholdWithPetsByEircode(eircode)
            .orElseThrow(() -> new NotFoundException(String.format("Household with eircode: %s not found.", eircode)));
    }

    @Override
    public List<Household> getHouseholdsWithNoPets(){
        return householdRepo.findHouseholdsWithNoPets();
    }
}
