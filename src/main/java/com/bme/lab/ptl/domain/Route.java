package com.bme.lab.ptl.domain;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-20
 */
public class Route {


    private String origin;
    private String destination;

    public Route(String origin, String destination) {
        this.origin = origin;
        this.destination = destination;
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getDestination() {
        return this.destination;
    }
}
