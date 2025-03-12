package com.mundorf.codechallenge;

import com.mundorf.codechallenge.region.RegionData;
import com.mundorf.codechallenge.region.RegionDataRepository;
import com.mundorf.codechallenge.region.CsvRegionReader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import java.util.List;

@SpringBootApplication
public class CodeChallengeApplication implements CommandLineRunner {

    @Autowired
    private CsvRegionReader csvRegionReader;

    @Autowired
    private RegionDataRepository regionDataRepository;

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(CodeChallengeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (environment.getProperty("csv.import.enabled", Boolean.class, true)) {
            loadCsvData();
        }
    }

    private void loadCsvData() {
        String filePath = "src/main/resources/postcodes.csv"; // Adjust the path if needed
        List<RegionData> regions = csvRegionReader.readRegionsFromCsv(filePath);
        if (!regions.isEmpty()) {
            regionDataRepository.saveAll(regions);
            System.out.println("Data has been saved to the database.");
        }
    }
}
