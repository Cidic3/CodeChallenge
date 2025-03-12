# CodeChallenge - Berechnung von Versicherungsprämien

Dies ist eine Spring-Boot-Anwendung zur Berechnung einer Versicherungsprämie basierend auf der Kilometerleistung des Fahrzeugs, dem Fahrzeugtyp und der Region der Zulassungsstelle.

## Funktionen

- Berechnung der Versicherungsprämie auf Basis der Kilometerleistung, des Fahrzeugtyps und der Region.
- Datenbankanbindung (MySQL) zur Speicherung der Benutzeranfragen und Ergebnisse.
- HTTP-API zur Berechnung der Prämie für Integrationen von Drittanbietern.
- Web-Oberfläche zur Benutzerinteraktion.

## Systemvoraussetzungen

- Java 23 oder höher
- MySQL-Datenbank
- Gradle (für den Build-Prozess)

## Installation und Ausführung

### 1. Klone das Repository:

```bash 
git clone https://github.com/dein-benutzername/CodeChallenge.git
```
### 2. Konfiguration der Datenbank

- Erstelle eine MySQL-Datenbank mit dem Namen ScopeVisio.
- Stelle sicher, dass die Zugangsdaten in der Datei src/main/resources/application.properties korrekt sind:

```bash 
spring.datasource.url=jdbc:mysql://localhost:3306/ScopeVisio
spring.datasource.username=<yourUsernameHere>
spring.datasource.password=<yourPasswordHere>
```

### 3. Starte das Projekt:

```bash 
gradle bootRun
```

### 4. Web-Oberfläche:

- Die Web-Oberfläche ist unter folgendem Link erreichbar:

```bash 
http://localhost:8080/praemie/formular
```

### 5. API-Endpunkte:

- **POST** /api/praemie/berechnen: Berechnet die Versicherungsprämie.
  - **Request Body:**

  ```bash 
  {
      "kilometerleistung": 12000,
      "fahrzeugtyp": 1,
      "postleitzahl": "12345"
  }
  ```
  - **Response:**
  ```bash
  {
      "praemie": 180.0
  }
  ```

## Datenbankstruktur

- Die Anwendung speichert die Anfragen und Berechnungen in einer MySQL-Datenbank. Die Haupttabelle könnte wie folgt aussehen:

| **id** | **anfrage_datum** | **fahrzeugtyp** | **kilometerleistung** | **postleitzahl** | **praemie** |
|--------|-------------------|-----------------|-----------------------|------------------|-------------|
| 1      | 2025-03-01        | 1               | 12000                 | 53859            | 1.5       |
| 2      | 2025-03-02        | 2               | 25000                 | 53859            | 4.0       |
| 3      | 2025-03-03        | 1               | 5000                  | 40215            | 0.5        |
| 4      | 2025-03-04        | 2               | 15000                 | 40215            | 3.0       |

### Wahl der Datenbank
Ich habe mich für MySQL als relationale Datenbank entschieden, da sie gut für die persistente Speicherung von strukturierten Daten geeignet ist. Sie bietet eine stabile und skalierbare Lösung für die Anforderungen dieses Projekts und ist eine weit verbreitete und gut unterstützte Option.

## Testen

Die Anwendung verwendet JUnit für Unit-Tests und Mockito für das Mocken von Services. Die Tests befinden sich im Ordner /src/test/java/com/mundorf/codechallenge.

- Beispieltests:
  - Kilometerleistungsfaktor: Überprüft, ob die Berechnung des Kilometerfaktors korrekt funktioniert.
  - Regionfaktor: Testet die Berechnung des Regionfaktors basierend auf dem Bundesland.
  - Berechnung der Prämie: Überprüft, ob die Prämie korrekt berechnet wird.
    
- Testkonzept:
  - Die Unit-Tests decken die wichtigsten Funktionen ab, wie z.B. die Berechnung der Prämie und das Verhalten der Services. Sie nutzen Mockito zum Mocken von Services und Abhängigkeiten, um isolierte Tests durchzuführen.

