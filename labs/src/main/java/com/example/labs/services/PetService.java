package com.example.labs.services;

import com.example.labs.dtos.PetRecord;
import com.example.labs.dtos.PetStats;
import com.example.labs.entities.Pet;
import com.example.labs.exceptions.NotFoundException;
import com.example.labs.repositories.PetRepository;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PetService implements PetServiceInterface {
    private final PetRepository petRepo;

    @Override
    public Pet createPet(Pet pet){
        return petRepo.save(pet);
    }

    @Override
    public List<Pet> getAllPets(){
        return petRepo.findAll();
    }

    @Override
    public Pet getPetById(Integer id) throws NotFoundException{
        return petRepo.findById(id)
            .orElseThrow(() -> new NotFoundException(String.format("Pet with id: %d not found.", id)));
    }

    @Override
    public Pet updatePetById(Integer id, Pet update) throws NotFoundException{
        Pet existing = getPetById(id);

        existing.setName(update.getName());
        existing.setAnimalType(update.getAnimalType());
        existing.setBreed(update.getBreed());
        existing.setAge(update.getAge());

        return petRepo.save(existing);
    }

    @Override
    public void deletePetById(Integer id) throws NotFoundException{
        Pet existing = getPetById(id);

        if (existing != null){
            petRepo.deleteById(id);
        }
    }

    @Override
    public int deleteAllPetsByName(String name){
        return petRepo.deleteByNameIgnoreCase(name);
    }

    @Override
    public List<Pet> getPetsByAnimalType(String animalType){
        return petRepo.findByAnimalType(animalType);
    }

    @Override
    public List<Pet> getPetsByBreed(String breed){
        return petRepo.findByBreedOrderByAge(breed);
    }

    @Override
    public List<PetRecord> getAllPetRecords(){
        return petRepo.findAllRecords();
    }

    @Override 
    public PetStats getPetStats(){
        return petRepo.getPetStats();
    }
}
