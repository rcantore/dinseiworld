package com.michead.dinseiworld.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Flick extends AbstractEntity {
    String title;
    LocalDate creationDate;
    Integer stars;

    @ManyToMany
    List<Character> characters;
}
