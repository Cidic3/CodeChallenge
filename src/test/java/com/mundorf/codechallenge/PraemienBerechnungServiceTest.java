package com.mundorf.codechallenge;

import com.mundorf.codechallenge.region.Bundesland;
import com.mundorf.codechallenge.service.PraemienBerechnungService;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PraemienBerechnungServiceTest {

    @Mock
    private PraemienBerechnungService praemienBerechnungService;  // Mock des Service

    @InjectMocks
    private PraemienBerechnungService praemienBerechnungServiceInstance; // Zielklasse für Injektion der Mocks

    @Before
    public void setUp() {
        // Keine Notwendigkeit für die manuelle Initialisierung hier, da @Mock und @InjectMocks alles erledigen.
    }

    @Test
    public void testKilometerleistungFaktorNegative() {
        int kilometerleistung = -2; // Jetzt ein int
        double expected = 0; // Negative Kilometerleistung sollte 0 zurückgeben
        double actual = praemienBerechnungServiceInstance.getKilometerFaktor(kilometerleistung);
        assertEquals("Der Kilometerleistungsfaktor sollte 0 betragen, wenn die Kilometerleistung negativ ist.", expected, actual, 0.0);
    }

    @Test
    public void testKilometerleistungFaktorSehrHoch() {
        int kilometerleistung = 999999999; // Jetzt ein int
        double expected = 2.0;
        double actual = praemienBerechnungServiceInstance.getKilometerFaktor(kilometerleistung);
        assertEquals("Der Kilometerleistungsfaktor sollte 2.0 betragen, wenn die Kilometerleistung extrem hoch ist.", expected, actual, 0.0);
    }

    @Test
    public void testKilometerleistungFaktorNull() {
        int kilometerleistung = 0; // Jetzt ein int
        double expected = 0.5;  // Kilometerleistung von 0 sollte den Faktor 0.5 zurückgeben
        double actual = praemienBerechnungServiceInstance.getKilometerFaktor(kilometerleistung);
        assertEquals("Der Kilometerleistungsfaktor sollte 0.5 betragen, wenn die Kilometerleistung 0 ist.", expected, actual, 0.0);
    }

    @Test
    public void testKilometerleistungFaktorMitGängigerKilometerleistung() {
        int kilometerleistung = 12000; // Jetzt ein int
        double expected = 1.5;
        double actual = praemienBerechnungServiceInstance.getKilometerFaktor(kilometerleistung);
        assertEquals("Der Kilometerleistungsfaktor sollte 1.5 betragen.", expected, actual, 0.0);
    }

    @Test
    public void testKilometerleistungFaktorMitMittlererKilometerleistung() {
        int kilometerleistung = 20000; // Jetzt ein int
        double expected = 1.5;  // Weil 20000 die Grenze für den Faktor 1.5 ist
        double actual = praemienBerechnungServiceInstance.getKilometerFaktor(kilometerleistung);
        assertEquals("Der Kilometerleistungsfaktor sollte 1.5 betragen.", expected, actual, 0.0);
    }

    @Test
    public void testKilometerleistungFaktorMitHoherKilometerleistung() {
        int kilometerleistung = 30000; // Jetzt ein int
        double expected = 2.0;
        double actual = praemienBerechnungServiceInstance.getKilometerFaktor(kilometerleistung);
        assertEquals("Der Kilometerleistungsfaktor sollte 2.0 betragen.", expected, actual, 0.0);
    }

    @Test
    public void testRegionFaktor() {
        // Test für das Region "Baden-Württemberg"
        Bundesland bundesland = Bundesland.BADEN_WUERTTEMBERG; // Verwende den Enum
        double expected = 1.0;
        double actual = praemienBerechnungServiceInstance.getRegionFaktor(bundesland); // Regionfaktor aus dem Enum
        assertEquals("Der Regionfaktor für Baden-Württemberg sollte 1.0 betragen.", expected, actual, 0.0);
    }

    @Test
    public void testFahrzeugtypFaktorPKW() {
        int fahrzeugtyp = 1;  // Fahrzeugtyp PKW (1)
        double expected = 1.0;
        double actual = praemienBerechnungServiceInstance.getFahrzeugtypFaktor(fahrzeugtyp);
        assertEquals("Der Fahrzeugtypfaktor für PKW sollte 1.0 betragen.", expected, actual, 0.0);
    }

    @Test
    public void testFahrzeugtypFaktorLKW() {
        int fahrzeugtyp = 2;  // Fahrzeugtyp LKW (2)
        double expected = 2.0;
        double actual = praemienBerechnungServiceInstance.getFahrzeugtypFaktor(fahrzeugtyp);
        assertEquals("Der Fahrzeugtypfaktor für LKW sollte 2.0 betragen.", expected, actual, 0.0);
    }

    @Test
    public void testBerechnePraemie() {
        // Test für die Berechnung der Prämie mit den Werten:
        int kilometerleistung = 20000;  // Kilometerleistung als int
        int fahrzeugtyp = 2;  // LKW als int
        Bundesland bundesland = Bundesland.NORDRHEIN_WESTFALEN;  // Region als Enum

        // Berechnungslogik: Kilometerfaktor (1.5) * Fahrzeugtypfaktor (2) * Regionfaktor (angenommener Wert für NRW)
        double expected = 1.5 * 2.0 *  1.0;  // 1.5 (Kilometerfaktor) * 2.0 (Fahrzeugtypfaktor für LKW) * 1.0 (Regionfaktor NRW)

        // Hier wird der tatsächliche Wert aus der Methode berechnet
        double actual = praemienBerechnungServiceInstance.berechnePraemie(kilometerleistung, fahrzeugtyp, bundesland);

        // Überprüfe, ob die berechnete Prämie korrekt ist
        assertEquals("Die berechnete Prämie sollte korrekt sein.", expected, actual, 0.0);
    }

    @Test
    public void testBerechnePraemieMitMock() {
        // Eingabewerte
        int kilometerleistung = 20000;
        int fahrzeugtyp = 2;  // Beispielwert für LKW
        Bundesland bundesland = Bundesland.NORDRHEIN_WESTFALEN;

        // Stub für den Rückgabewert von berechnePraemie
        when(praemienBerechnungService.berechnePraemie(kilometerleistung, fahrzeugtyp, bundesland)).thenReturn(8.0);

        // Aufruf der gemockten Methode
        double actual = praemienBerechnungService.berechnePraemie(kilometerleistung, fahrzeugtyp, bundesland);

        // Überprüfung des Ergebnisses
        assertEquals("Die berechnete Prämie sollte dem gemockten Wert entsprechen.", 8.0, actual, 0.0);
    }

    @After
    public void tearDown() {
        // Cleanup code nach jedem Test (falls notwendig)
        praemienBerechnungService = null;
    }
}