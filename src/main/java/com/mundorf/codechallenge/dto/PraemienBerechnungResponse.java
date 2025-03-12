package com.mundorf.codechallenge.dto;

public class PraemienBerechnungResponse {

    private double praemie;

    public PraemienBerechnungResponse(double praemie) {
        this.praemie = praemie;
    }

    public double getPraemie() {
        return praemie;
    }

    public void setPraemie(double praemie) {
        this.praemie = praemie;
    }
}
