package com.example.labs.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.labs.entities.Pet;
import com.example.labs.exceptions.NotFoundException;
import com.example.labs.services.PetService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class GraphQLAPI {
    private final PetService petService;

    @QueryMapping
    Pet getPet(@Argument int id) throws NotFoundException{
        return petService.getPetById(id);
    }

    @MutationMapping
    int deletePet(@Argument int id) throws NotFoundException{
        petService.deletePetById(id);
        return 1;
    }
}