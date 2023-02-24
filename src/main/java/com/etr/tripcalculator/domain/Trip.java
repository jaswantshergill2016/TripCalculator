package com.etr.tripcalculator.domain;

import lombok.Data;

@Data
public class Trip {

    private String source;
    private String destination;
    private Double distance;
    private Double cost;
}
