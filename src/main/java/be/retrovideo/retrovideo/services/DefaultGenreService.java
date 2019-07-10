package be.retrovideo.retrovideo.services;

import be.retrovideo.retrovideo.domain.Genre;
import be.retrovideo.retrovideo.repositories.GenreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultGenreService implements GenreService{
    private final GenreRepository repository;

    public DefaultGenreService(GenreRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
    public List<Genre> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true , isolation = Isolation.READ_COMMITTED)
    public Optional<Genre> findById(long id) {
        return repository.findById(id);
    }
}
