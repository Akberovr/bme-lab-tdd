package com.bme.lab.ptl.service;

import com.bme.lab.ptl.domain.Route;
import org.springframework.stereotype.Service;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@Service
public class RouteService {

    public Route getRoutes() {
        return new Route("","");
    }
}
