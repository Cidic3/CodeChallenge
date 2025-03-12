package com.mundorf.codechallenge.praemienanfrage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Dieses Repository ist verantwortlich für die Speicherung und Abfrage von
 * Prämienanfragen in der Datenbank.
 */

@Repository
public interface PraemienAnfrageRepository extends JpaRepository<PraemienAnfrage, Long> {
}
