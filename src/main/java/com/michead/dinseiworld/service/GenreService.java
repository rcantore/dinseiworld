package com.michead.dinseiworld.service;

import com.michead.dinseiworld.model.Genre;
import com.michead.dinseiworld.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService {
    private GenreRepository genreRepository;

    public List<Genre> getAllGenres() {
        List<Genre> all = genreRepository.findAll();

        return all;
    }
}
