package com.etr.tripcalculator;


import com.etr.tripcalculator.domain.Interchanges;
import com.etr.tripcalculator.domain.LocationDesc;
import com.etr.tripcalculator.service.Calculator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;

@SpringBootApplication
@Slf4j
public class TripCalculatorApplication implements CommandLineRunner {

	@Autowired
	private Calculator calculator;

	public static void main(String[] args) throws IOException {

		SpringApplication.run(TripCalculatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Interchanges interchanges = calculator.readInterChangesJSONFile("interchanges.json");
		String source ="Salem Road";
		String destination = "QEW";

		source ="QEW";
		destination = "Salem Road";

		Double distance = calculator.calculateDistance(source,destination,interchanges);

		log.info("Total distance "+distance);

		Double costOfTrip = calculator.calculateCostOfTrip(distance);

		log.info("Cost of Trip "+ costOfTrip);

	}
}
