package com.mundorf.codechallenge.region;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CsvRegionReader {

    public List<RegionData> readRegionsFromCsv(String csvPath) {
        Map<String, RegionData> regionDataMap = new HashMap<>();

        try (CSVReader reader = new CSVReader(new FileReader(csvPath))) {
            // Header-Zeile überspringen
            reader.readNext();

            String[] record;
            while ((record = reader.readNext()) != null) {
                // Überprüfen, ob die Zeile genügend Felder hat
                if (record.length >= 7) {
                    String plz = record[6].trim().replace("\"", "");
                    String bundeslandStr = record[2];

                    // Bundesland-Enum aus dem String ermitteln
                    Bundesland bundesland = Bundesland.fromString(bundeslandStr);

                    // Nur einfügen, wenn die PLZ noch nicht existiert und das Bundesland gültig ist
                    if (!regionDataMap.containsKey(plz) && bundesland != null) {
                        RegionData regionData = new RegionData();
                        regionData.setPostleitzahl(plz);
                        regionData.setBundesland(bundesland);

                        regionDataMap.put(plz, regionData);
                    }
                }
            }
        } catch (IOException | CsvException e) {
            System.err.println("Fehler beim Lesen der CSV-Datei: " + e.getMessage());
            e.printStackTrace();
        }

        return new ArrayList<>(regionDataMap.values());
    }
}
