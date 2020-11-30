package com.bme.lab.ptl.rest;

import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@RestController
class RouteController {

    private RouteService routeService;

    RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/routes")
    private Route getRoute(){
        return routeService.getRoutes();
    }

}
