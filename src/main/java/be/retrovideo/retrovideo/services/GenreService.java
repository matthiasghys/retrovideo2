package be.retrovideo.retrovideo.services;

import be.retrovideo.retrovideo.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> findAll();
    Optional<Genre> findById(long id);

}
