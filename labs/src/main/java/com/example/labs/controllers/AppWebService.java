package com.example.labs.controllers;

import com.example.labs.dtos.PetData;
import com.example.labs.dtos.PetStats;
import com.example.labs.entities.Household;
import com.example.labs.entities.Pet;
import com.example.labs.exceptions.NotFoundException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.labs.services.HouseholdService;
import com.example.labs.services.PetService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/api")
public class AppWebService {
    private PetService petService;
    private HouseholdService householdService;

    @PostMapping("/pets")
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(@RequestBody @Valid PetData petData) throws NotFoundException{
        Household petHousehold = householdService.getHouseholdByEircode(petData.eircode());
        return petService.createPet(new Pet(
            petData.name(),
            petData.animalType(),
            petData.breed(),
            petData.age(),
            petHousehold
        ));
    }

    @GetMapping("/pets")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/pets/{id}")
    public Pet getPetById(@PathVariable int id) throws NotFoundException{
        return petService.getPetById(id);
    }

    @DeleteMapping("/pets/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePetById(@PathVariable int id) throws NotFoundException{
        petService.deletePetById(id);
    }

    @PatchMapping("/pets/{id}")
    public Pet updatePetById(@PathVariable int id, @RequestBody @Valid PetData petData) throws NotFoundException{
        Household updatedHousehold = householdService.getHouseholdByEircode(petData.eircode());
        return petService.updatePetById(id, new Pet(
            petData.name(),
            petData.animalType(),
            petData.breed(),
            petData.age(),
            updatedHousehold
        ));
    }

    @GetMapping("/pets/stats")
    public PetStats getPetStats(){
        return petService.getPetStats();
    }
    
    @GetMapping("/households/{eircode}")
    public Household getHouseholdByEircode(
        @PathVariable String eircode,
        @RequestParam(defaultValue = "false") boolean includesPets
        ) throws NotFoundException{
            // call method depending on includesPets flag param
            return includesPets ? householdService.getHouseholdWithPetsByEircode(eircode) : householdService.getHouseholdByEircode(eircode);
    }

    @GetMapping("/households/nopet")
    public List<Household> getHouseholdsWithNoPets(){
        return householdService.getHouseholdsWithNoPets();
    }
}