package be.retrovideo.retrovideo.domain;

public class Klant {
    private final long id;
    private final String familieNaam;
    private final String voorNaam;
    private final String straatNummer;
    private final int postcode;
    private final String gemeente;

    public Klant(String familieNaam, String voorNaam, String straatNummer, int postcode, String gemeente, long id) {
        this.familieNaam = familieNaam;
        this.voorNaam = voorNaam;
        this.straatNummer = straatNummer;
        this.postcode = postcode;
        this.gemeente = gemeente;
        this.id= id;
    }

    public long getId() {
        return id;
    }

    public String getFamilieNaam() {
        return familieNaam;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public String getStraatNummer() {
        return straatNummer;
    }

    public int getPostcode() {
        return postcode;
    }

    public String getGemeente() {
        return gemeente;
    }
}
