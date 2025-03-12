package com.mundorf.codechallenge.dto;

import com.mundorf.codechallenge.region.Bundesland;
import com.mundorf.codechallenge.region.RegionData;

public class RegionDto {

    private String postleitzahl;
    private String bundesland;
    private double faktor;

    public RegionDto(RegionData regionData) {
        this.postleitzahl = regionData.getPostleitzahl();
        Bundesland bl = regionData.getBundesland();
        this.bundesland = bl.name(); // Enum als String
        this.faktor = bl.getFaktor(); // Faktor aus Enum
    }

    public String getPostleitzahl() {
        return postleitzahl;
    }

    public String getBundesland() {
        return bundesland;
    }

    public double getFaktor() {
        return faktor;
    }
}
