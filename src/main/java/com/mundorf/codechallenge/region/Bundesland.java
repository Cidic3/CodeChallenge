package com.mundorf.codechallenge.region;

public enum Bundesland {
    BADEN_WUERTTEMBERG(1.0),
    BAYERN(1.1),
    BERLIN(1.2),
    BRANDENBURG(0.9),
    BREMEN(1.0),
    HAMBURG(1.2),
    HESSEN(1.0),
    MECKLENBURG_VORPOMMERN(0.8),
    NIEDERSACHSEN(0.95),
    NORDRHEIN_WESTFALEN(1.0),
    RHEINLAND_PFALZ(0.95),
    SAARLAND(1.0),
    SACHSEN(0.9),
    SACHSEN_ANHALT(0.85),
    SCHLESWIG_HOLSTEIN(0.95),
    THUERINGEN(0.85);

    private final double faktor;

    Bundesland(double faktor) {
        this.faktor = faktor;
    }

    public double getFaktor() {
        return faktor;
    }

    public static Bundesland fromString(String value) {
        if (value == null) {
            return null; // Null überprüfen
        }

        try {
            // Versuche direkt den enum-Wert zu finden (für den Fall, dass die Werte bereits in Großbuchstaben und mit Unterstrichen sind)
            return Bundesland.valueOf(value.toUpperCase().replace("-", "_"));
        } catch (IllegalArgumentException e) {
            // Wenn das nicht funktioniert, versuche die manuelle Zuordnung

            // Eingabe normalisieren: Leerzeichen und Bindestriche entfernen und alles in Kleinbuchstaben umwandeln
            String normalizedValue = value.replace(" ", "")
                    .replace("-", "")
                    .replace("_", "")
                    .replace("ü", "ue")  // Normalize ü to ue
                    .replace("\"", "")
                    .toLowerCase();

            switch (normalizedValue) {
                case "badenwuerttemberg":
                case "badenwürttemberg":
                    return BADEN_WUERTTEMBERG;
                case "bayern":
                    return BAYERN;
                case "berlin":
                    return BERLIN;
                case "brandenburg":
                    return BRANDENBURG;
                case "bremen":
                    return BREMEN;
                case "hamburg":
                    return HAMBURG;
                case "hessen":
                    return HESSEN;
                case "mecklenburgvorpommern":
                    return MECKLENBURG_VORPOMMERN;
                case "niedersachsen":
                    return NIEDERSACHSEN;
                case "nordrheinwestfalen":
                    return NORDRHEIN_WESTFALEN;
                case "rheinlandpfalz":
                    return RHEINLAND_PFALZ;
                case "saarland":
                    return SAARLAND;
                case "sachsen":
                    return SACHSEN;
                case "sachsenanhalt":
                    return SACHSEN_ANHALT;
                case "schleswigholstein":
                    return SCHLESWIG_HOLSTEIN;
                case "thueringen":
                case "thüringen":
                    return THUERINGEN;
                default:
                    System.err.println("Unbekanntes Bundesland: '" + value + "', normalisiert: '" + normalizedValue + "'");
                    return null; // Gibt null zurück, falls kein passendes Bundesland gefunden wird
            }
        }
    }
}
