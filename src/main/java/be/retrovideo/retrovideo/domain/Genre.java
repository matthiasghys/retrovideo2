package be.retrovideo.retrovideo.domain;

public class Genre {
    private final long id;
    private final String naam;

    public Genre(String naam, long id) {
        this.naam = naam;
        this.id= id;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

}
