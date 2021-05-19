package com.bme.lab.ptl.rest;

import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.service.route.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@RestController
@RequiredArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("company/routes")
    public Iterable<Route> getRoute() {
        return routeService.findAll();
    }

    @GetMapping("company/{companyId}/routes/{id}")
    public ResponseEntity<Route> getRouteByIdAndCompanyId(@PathVariable(value = "companyId") Long companyId,
                                              @PathVariable(value = "id") Long id) {
        return routeService.findByIdAndCompanyId(id, companyId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/company/{companyId}/routes")
    @ResponseStatus(HttpStatus.CREATED)
    public Route create(@PathVariable(value = "companyId") Long companyId,
                        @RequestBody @Valid Route route) {
        return routeService.createRoute(companyId, route);
    }

    @DeleteMapping("/company/{companyId}/routes/{routeId}")
    public ResponseEntity<?> deleteRoute(@PathVariable(value = "companyId") Long companyId,
                                           @PathVariable(value = "routeId") Long routeId) {
        routeService.delete(companyId, routeId);
        return ResponseEntity.ok().build();
    }

}
