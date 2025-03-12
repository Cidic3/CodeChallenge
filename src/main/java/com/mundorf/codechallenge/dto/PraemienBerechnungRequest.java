package com.mundorf.codechallenge.dto;

public class PraemienBerechnungRequest {

    private int kilometerleistung;
    private int fahrzeugtyp;
    private String postleitzahl;

    public int getKilometerleistung() {
        return kilometerleistung;
    }

    public void setKilometerleistung(int kilometerleistung) {
        this.kilometerleistung = kilometerleistung;
    }

    public int getFahrzeugtyp() {
        return fahrzeugtyp;
    }

    public void setFahrzeugtyp(int fahrzeugtyp) {
        this.fahrzeugtyp = fahrzeugtyp;
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }
}
