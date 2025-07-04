package com.michead.dinseiworld.model;

import lombok.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Character extends AbstractEntity {
    String name;
    Integer age;
    Float weight;
    String shortBio;

    @ManyToMany
    List<Flick> flicks = new ArrayList<>();
}
