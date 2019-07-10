package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    List<Genre> findAll();
    Optional<Genre> findById(long id);
}
