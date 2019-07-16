package be.retrovideo.retrovideo.repositories;

import be.retrovideo.retrovideo.domain.Film;
import be.retrovideo.retrovideo.domain.Reservatie;

public interface ReservatieRepository {

     void create(Reservatie reservatie);
     void updateGereserveerdeFilm(Film film);



}
