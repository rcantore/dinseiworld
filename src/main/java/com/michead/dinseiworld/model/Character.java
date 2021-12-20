package com.michead.dinseiworld.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Data
public class Character extends AbstractEntity {
    String name;
    Integer age;
    Float weight;
    String shortBio;

    @ManyToMany
    List<Flick> flicks;
}
