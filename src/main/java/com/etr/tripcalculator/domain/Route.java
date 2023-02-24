package com.etr.tripcalculator.domain;

public class Route {
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