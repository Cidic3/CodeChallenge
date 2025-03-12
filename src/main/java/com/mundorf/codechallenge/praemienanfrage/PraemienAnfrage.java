package com.mundorf.codechallenge.praemienanfrage;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

/**
 * Diese Klasse repräsentiert die Prämienanfrage und speichert alle relevanten Parameter
 * sowie das Ergebnis der Prämienberechnung in der Datenbank.
 */
@Entity
public class PraemienAnfrage {

    // Die eindeutige ID für jede Anfrage
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kilometerleistung des Fahrzeugs
    private int kilometerleistung;

    // Fahrzeugtyp, der die Berechnung beeinflusst
    private int fahrzeugtyp;

    // Postleitzahl, die verwendet wird, um das Bundesland zu ermitteln
    private String postleitzahl;

    // Das berechnete Prämienergebnis
    private double praemie;

    // Datum und Uhrzeit der Anfrage
    private LocalDateTime anfrageDatum;

    // Konstruktoren, Getter und Setter

    public PraemienAnfrage(int kilometerleistung, int fahrzeugtyp, String postleitzahl, double praemie, LocalDateTime anfrageDatum) {
        this.kilometerleistung = kilometerleistung;
        this.fahrzeugtyp = fahrzeugtyp;
        this.postleitzahl = postleitzahl;
        this.praemie = praemie;
        this.anfrageDatum = anfrageDatum;
    }

    public PraemienAnfrage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getPraemie() {
        return praemie;
    }

    public void setPraemie(double praemie) {
        this.praemie = praemie;
    }

    public LocalDateTime getAnfrageDatum() {
        return anfrageDatum;
    }

    public void setAnfrageDatum(LocalDateTime anfrageDatum) {
        this.anfrageDatum = anfrageDatum;
    }
}
