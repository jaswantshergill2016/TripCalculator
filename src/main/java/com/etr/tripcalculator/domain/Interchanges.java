package com.etr.tripcalculator.domain;


import java.util.HashMap;
import java.util.Map;

public class Interchanges {
    Map<String, LocationDesc> locations = new HashMap<>();

    public Map<String, LocationDesc> getMap() {
        return locations;
    }

    public void setMap(Map<String, LocationDesc> map) {
        this.locations = map;
    }
}






