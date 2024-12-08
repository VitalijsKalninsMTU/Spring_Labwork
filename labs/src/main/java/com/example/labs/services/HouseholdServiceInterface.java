package com.example.labs.services;

import java.util.List;

import com.example.labs.entities.Household;
import com.example.labs.exceptions.NotFoundException;

public interface HouseholdServiceInterface {
    Household getHouseholdByEircode(String eircode) throws NotFoundException;
    Household getHouseholdWithPetsByEircode(String eircode) throws NotFoundException;
    List<Household> getHouseholdsWithNoPets();
}
