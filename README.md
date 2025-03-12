# CodeChallenge - Versicherungsprämie Berechnung

Dies ist eine Spring-Boot-Anwendung zur Berechnung einer Versicherungsprämie basierend auf der Kilometerleistung des Fahrzeugs, dem Fahrzeugtyp und der Region der Zulassungsstelle.

## Funktionen

- Berechnung der Versicherungsprämie auf Basis der Kilometerleistung, des Fahrzeugtyps und der Region.
- Datenbankanbindung (MySQL) zur Speicherung der Benutzeranfragen und Ergebnisse.
- HTTP-API zur Berechnung der Prämie für Integrationen von Drittanbietern.
- Web-Oberfläche zur Benutzerinteraktion.

## Systemvoraussetzungen

- Java 11 oder höher
- MySQL-Datenbank
- Gradle (für den Build-Prozess)

## Installation und Ausführung

### 1. Klone das Repository:
```bash
git clone https://github.com/dein-benutzername/CodeChallenge.git


## Konfiguration der Datenbank
Erstelle eine MySQL-Datenbank mit dem Namen ScopeVisio.
Stelle sicher, dass die Zugangsdaten in der Datei src/main/resources/application.properties korrekt sind:

spring.datasource.url=jdbc:mysql://localhost:3306/ScopeVisio
spring.datasource.username=<yourUsernameHere>
spring.datasource.password=<yourPasswordHere>



