package com.etr.tripcalculator.controller;


import com.etr.tripcalculator.domain.Interchanges;
import com.etr.tripcalculator.service.Calculator;
import com.etr.tripcalculator.domain.LocationDesc;
import com.etr.tripcalculator.domain.Trip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import org.springframework.http.HttpStatus;

@RestController
@Slf4j
public class Controller {

    @Autowired
    private Calculator calculator;

    @PostMapping("/trip")
    public ResponseEntity<Trip> login(@RequestBody Trip trip) throws IOException {
        Interchanges interchanges = calculator.readInterChangesJSONFile();

        Double distance = calculator.calculateDistance(trip.getSource(),trip.getDestination(),interchanges);

        log.info("Total distance "+distance);

        Double costOfTrip = calculator.calculateCostOfTrip(distance);

        log.info("Cost of Trip "+ costOfTrip);

        Trip responseTrip = new Trip();
        responseTrip.setSource(trip.getSource());
        responseTrip.setDestination(trip.getDestination());
        responseTrip.setDistance(distance);
        responseTrip.setCost(costOfTrip);
        return new ResponseEntity<>(responseTrip, null, HttpStatus.OK);
    }
}
