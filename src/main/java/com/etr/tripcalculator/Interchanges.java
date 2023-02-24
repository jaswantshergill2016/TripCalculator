package com.etr.tripcalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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



class LocationDesc {

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

class Route {
    Integer toId;
    Double distance;

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Route{" +
                "toId=" + toId +
                ", distance=" + distance +
                '}';
    }
}


