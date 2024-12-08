package com.example.labs.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "household")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Household {
    @Id
    private String eircode;

    private int numberOfOccupants;

    private int maxNumberOfOccupants;

    private boolean ownerOccupied;

    @OneToMany(mappedBy = "household", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pet> pets;
}
