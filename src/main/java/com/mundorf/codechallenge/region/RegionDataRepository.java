package com.mundorf.codechallenge.region;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionDataRepository extends JpaRepository<RegionData, String> {
    RegionData findByPostleitzahl(String postleitzahl);
}
