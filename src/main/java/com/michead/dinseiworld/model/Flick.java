package com.michead.dinseiworld.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Flick extends AbstractEntity {
    String title;
    LocalDate creationDate;
    Integer stars;

    @ManyToMany
    List<Character> characters = new ArrayList<>();
}
