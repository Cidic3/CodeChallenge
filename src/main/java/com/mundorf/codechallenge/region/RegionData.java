package com.mundorf.codechallenge.region;

import jakarta.persistence.*;

@Entity
public class RegionData {

    @Id
    private String postleitzahl;

    @Enumerated(EnumType.STRING)
    private Bundesland bundesland;

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public void setPostleitzahl(String postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public Bundesland getBundesland() {
        return bundesland;
    }

    public void setBundesland(Bundesland bundesland) {
        this.bundesland = bundesland;
    }

    public void setBundeslandFromString(String bundeslandStr) {
        this.bundesland = Bundesland.fromString(bundeslandStr);
    }

    @Override
    public String toString() {
        return "RegionData{" +
                "postleitzahl='" + postleitzahl + '\'' +
                ", bundesland=" + bundesland +
                '}';
    }
}
