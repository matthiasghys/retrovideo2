package be.retrovideo.retrovideo.domain;

import java.math.BigDecimal;

public class Film {
    private final long id;
    private final String titel;
    private final int voorraad;
    private final int gereserveerd;
    private final BigDecimal prijs;
    private final long genreId;

    public Film(String titel, int voorraad, int gereserveerd, BigDecimal prijs, long genreId, long id) {
        this.titel = titel;
        this.voorraad = voorraad;
        this.gereserveerd = gereserveerd;
        this.prijs = prijs;
        this.genreId = genreId;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getGereserveerd() {
        return gereserveerd;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public long getGenreId() {
        return genreId;
    }

    public boolean isBeschikbaar(){
        return this.voorraad - this.gereserveerd > 0;
    }
}
