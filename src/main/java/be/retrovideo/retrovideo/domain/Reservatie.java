package be.retrovideo.retrovideo.domain;

import java.time.LocalDateTime;

public class Reservatie {
    private final Klant klant;
    private final Film film;
    private final LocalDateTime reservatie;

    public Reservatie(Klant klant, Film film, LocalDateTime reservatie) {
        this.klant = klant;
        this.film = film;
        this.reservatie = reservatie;
    }

    public Klant getKlant() {
        return klant;
    }

    public Film getFilm() {
        return film;
    }

    public LocalDateTime getReservatie() {
        return reservatie;
    }
}




