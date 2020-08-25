package com.ag04smarts.sha;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

@SpringBootApplication
public class SHAApplication {

    private static final String SAMPLE_CSV_FILE = "./sample.csv";

    public static void main(String[] args) throws IOException {
        SpringApplication.run(SHAApplication.class, args);

        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(SAMPLE_CSV_FILE));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("ID", "Firstname", "Lastname", "Email", "Gender"));
        ) {
            csvPrinter.printRecord(Arrays.asList("1", "Ana", "Tomic", "anatomic@gmail.com", "female"));

            csvPrinter.flush();
        }
    }
}
