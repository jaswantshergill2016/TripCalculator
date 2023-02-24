package com.etr.tripcalculator.domain;

import java.util.ArrayList;
import java.util.List;

public class LocationDesc {

    String name;
    Double lat;
    Double lng;

    List<Route> routes = new ArrayList<>();

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLog() {
        return lng;
    }

    public void setLog(Double log) {
        this.lng = log;
    }

    @Override
    public String toString() {
        return "LocationDesc{" +
                "name='" + name + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", routes=" + routes +
                '}';
    }
}

