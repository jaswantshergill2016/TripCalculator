package com.etr.tripcalculator.service;

import com.etr.tripcalculator.domain.Interchanges;
import com.etr.tripcalculator.domain.Trip;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @InjectMocks
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateCostOfTrip() throws IOException {

        Interchanges interchanges = calculator.readInterChangesJSONFile();
        Double tripDistance = calculator.calculateDistance("QEW","Salem Road",interchanges);
        Double costOfTrip = calculator.calculateCostOfTrip(tripDistance);
        assertEquals(new Double(28.819250000000004),costOfTrip);
    }

    @Test
    void calculateDistance() throws IOException {
        Interchanges interchanges = calculator.readInterChangesJSONFile();
        Double tripDistance = calculator.calculateDistance("QEW","Salem Road",interchanges);
        assertEquals(new Double(115.27700000000002),tripDistance);
    }

    @Test
    void readInterChangesJSONFile() throws IOException {

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        File file = ResourceUtils.getFile("classpath:interchanges.json");
        String content = new String(Files.readAllBytes(file.toPath()));
        Interchanges interchanges = gson.fromJson(content, Interchanges.class);
        String expectedString = gson.toJson(interchanges);

        assertEquals(expectedString,gson.toJson(calculator.readInterChangesJSONFile()));

    }
}