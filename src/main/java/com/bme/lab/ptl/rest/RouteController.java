package com.bme.lab.ptl.rest;

import com.bme.lab.ptl.domain.Route;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@RestController
class RouteController {

    @GetMapping("/routes")
    private Route getRoute(){
        return null;
    }

}
