package com.mundorf.codechallenge.controller;

import com.mundorf.codechallenge.dto.PraemienBerechnungRequest;
import com.mundorf.codechallenge.service.PraemienBerechnungService;
import com.mundorf.codechallenge.service.RegionService;
import com.mundorf.codechallenge.region.RegionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/praemie")
public class WebController {

    private final PraemienBerechnungService praemienBerechnungService;
    private final RegionService regionService;

    @Autowired
    public WebController(PraemienBerechnungService praemienBerechnungService, RegionService regionService) {
        this.praemienBerechnungService = praemienBerechnungService;
        this.regionService = regionService;
    }

    @GetMapping("/formular")
    public String showForm(Model model) {

        model.addAttribute("request", new PraemienBerechnungRequest());
        return "praemie-berechnung";
    }

    @PostMapping("/berechnen")
    public String berechnePraemie(@ModelAttribute PraemienBerechnungRequest request, Model model) {

        // Sicherstellen, dass das Formular zurückgesetzt wird
        model.addAttribute("request", request);

        // Fehler-Handling für ungültige Eingaben
        if (request.getKilometerleistung() <= 0) {
            model.addAttribute("error", "Die Kilometerleistung muss größer als 0 sein.");
            return "praemie-berechnung";
        }

        if (request.getFahrzeugtyp() != 1 && request.getFahrzeugtyp() != 2) {
            model.addAttribute("error", "Ungültiger Fahrzeugtyp! Bitte wählen Sie PKW oder LKW.");
            return "praemie-berechnung";
        }

        RegionData regionData = regionService.getRegionByPostleitzahl(request.getPostleitzahl());
        if (regionData == null) {
            model.addAttribute("error", "Ungültige Postleitzahl! Bitte geben Sie eine gültige PLZ ein.");
            return "praemie-berechnung";
        }

        // Prämie berechnen
        double praemie = praemienBerechnungService.berechnePraemie(
                request.getKilometerleistung(),
                request.getFahrzeugtyp(),
                regionData.getBundesland()
        );

        model.addAttribute("praemie", praemie);
        return "praemie-berechnung";
    }
}
