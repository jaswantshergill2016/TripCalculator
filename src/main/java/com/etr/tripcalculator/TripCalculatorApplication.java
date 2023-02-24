package com.etr.tripcalculator;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;

@SpringBootApplication
public class TripCalculatorApplication implements CommandLineRunner {

	@Autowired
	private Calculator calculator;

	public static void main(String[] args) throws IOException {

		SpringApplication.run(TripCalculatorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Map<String,LocationDesc> map = calculator.readInterChangesJSONFile();
		String source ="Salem Road";
		String destination = "QEW";

		source ="QEW";
		destination = "Salem Road";

		Double distance = calculator.calculateDistance(source,destination,map);

		System.out.println("Total distance "+distance);

		Double costOfTrip = calculator.calculateCostOfTrip(distance);

		System.out.println("Cost of Trip "+ costOfTrip);

	}
}
