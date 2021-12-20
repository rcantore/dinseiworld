package com.michead.dinseiworld.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Genre extends AbstractEntity {
    String name;
    @OneToMany
    List<Flick> flicks;
}
