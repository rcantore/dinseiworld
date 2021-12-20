package com.michead.dinseiworld.repository;

import com.michead.dinseiworld.model.Flick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlickRepository extends JpaRepository<Flick, Long> {
}
