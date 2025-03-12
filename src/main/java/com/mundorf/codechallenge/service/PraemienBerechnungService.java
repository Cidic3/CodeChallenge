package com.mundorf.codechallenge.service;

import com.mundorf.codechallenge.region.Bundesland;
import org.springframework.stereotype.Service;

@Service
public class PraemienBerechnungService {

    public double berechnePraemie(int kilometerleistung, int fahrzeugtyp, Bundesland bundesland) {
        double kilometerleistungFaktor = getKilometerFaktor(kilometerleistung);
        double fahrzeugtypFaktor = getFahrzeugtypFaktor(fahrzeugtyp);
        double regionFaktor = getRegionFaktor(bundesland);
        return kilometerleistungFaktor * fahrzeugtypFaktor * regionFaktor;
    }

    public double getRegionFaktor(Bundesland bundesland) {
        return bundesland.getFaktor();
    }

    public double getFahrzeugtypFaktor(int fahrzeugtyp) {
        if (fahrzeugtyp < 0) {
            return 0;
        } else if (fahrzeugtyp == 1) {
            return 1;
        } else if (fahrzeugtyp == 2) {
            return 2;
        }
        return 0;
    }

    public double getKilometerFaktor(int kilometerleistung) {
        if (kilometerleistung < 0) {
            return 0;
        } else if (kilometerleistung <= 5000) {
            return 0.5;
        } else if (kilometerleistung <= 10000) {
            return 1.0;
        } else if (kilometerleistung <= 20000) {
            return 1.5;
        } else {
            return 2.0;
        }
    }
}
