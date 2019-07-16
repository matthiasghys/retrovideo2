package be.retrovideo.retrovideo.services;

import be.retrovideo.retrovideo.domain.Reservatie;
import be.retrovideo.retrovideo.repositories.ReservatieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional( isolation = Isolation.REPEATABLE_READ)

public class DefaultReservatieService implements ReservatieService {
    private final ReservatieRepository repository;

    public DefaultReservatieService(ReservatieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void create(Reservatie reservatie) {
         repository.create(reservatie);
    }


}
