package com.mundorf.codechallenge.controller;

import com.mundorf.codechallenge.dto.PraemienBerechnungRequest;
import com.mundorf.codechallenge.dto.RegionDto;
import com.mundorf.codechallenge.praemienanfrage.PraemienAnfrage;
import com.mundorf.codechallenge.praemienanfrage.PraemienAnfrageRepository;
import com.mundorf.codechallenge.region.RegionData;
import com.mundorf.codechallenge.service.PraemienBerechnungService;
import com.mundorf.codechallenge.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/praemie")
public class PraemienBerechnungController {

    private final RegionService regionService;
    private final PraemienBerechnungService praemienBerechnungService;
    private final PraemienAnfrageRepository praemienAnfrageRepository;

    @Autowired
    public PraemienBerechnungController(RegionService regionService,
                                        PraemienBerechnungService praemienBerechnungService,
                                        PraemienAnfrageRepository praemienAnfrageRepository) {
        this.regionService = regionService;
        this.praemienBerechnungService = praemienBerechnungService;
        this.praemienAnfrageRepository = praemienAnfrageRepository;
    }

    @PostMapping("/berechnen")
    public ResponseEntity<Map<String, Object>> berechnePraemie(@RequestBody PraemienBerechnungRequest request) {

        Map<String, Object> response = new HashMap<>();

        // Validierung für die Kilometerleistung
        if (request.getKilometerleistung() <= 0) {
            response.put("error", "Kilometerleistung muss größer als 0 sein.");
            return ResponseEntity.badRequest().body(response);
        }

        // Validierung für den Fahrzeugtyp
        if (request.getFahrzeugtyp() <= 0) {
            response.put("error", "Fahrzeugtyp muss eine positive Zahl sein.");
            return ResponseEntity.badRequest().body(response);
        }

        // Bundesland ermitteln anhand der Postleitzahl
        RegionData regionData = regionService.getRegionByPostleitzahl(request.getPostleitzahl());
        if (regionData == null) {
            response.put("error", "Es konnte kein Bundesland für die folgende Postleitzahl gefunden werden: " + request.getPostleitzahl());
            return ResponseEntity.badRequest().body(response);
        }

        // Prämie berechnen paemie
        double praemie = praemienBerechnungService.berechnePraemie(
                request.getKilometerleistung(),
                request.getFahrzeugtyp(),
                regionData.getBundesland()
        );

        // Prämie speichern
        PraemienAnfrage praemienAnfrage = new PraemienAnfrage(
                request.getKilometerleistung(),
                request.getFahrzeugtyp(),
                request.getPostleitzahl(),
                praemie,
                LocalDateTime.now()
        );
        praemienAnfrageRepository.save(praemienAnfrage);

        // Erfolgreiche Antwort mit DTO und Premium
        response.put("premium", praemie);
        response.put("region", new RegionDto(regionData)); // Sauber über DTO zurückgeben
        return ResponseEntity.ok(response);
    }
}
