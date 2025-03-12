package com.mundorf.codechallenge.service;

import com.mundorf.codechallenge.region.CsvRegionReader;
import com.mundorf.codechallenge.region.RegionData;
import com.mundorf.codechallenge.region.RegionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionDataRepository regionDataRepository;

    @Autowired
    private CsvRegionReader csvRegionReader;

    public void loadRegionsFromCsvAndSave(String csvPath) {
        List<RegionData> regions = csvRegionReader.readRegionsFromCsv(csvPath);
        regionDataRepository.saveAll(regions);
    }

    public List<RegionData> getAllRegions() {
        return regionDataRepository.findAll();
    }

    public RegionData getRegionByPostleitzahl(String plz) {
        return regionDataRepository.findByPostleitzahl(plz);
    }
}
