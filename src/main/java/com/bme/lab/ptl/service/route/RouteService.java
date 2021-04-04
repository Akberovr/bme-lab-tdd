package com.bme.lab.ptl.service.route;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.domain.Route;

import java.util.List;
import java.util.Optional;


public interface RouteService {
    Iterable<Route> findAll();

    Optional<Route> findByIdAndCompanyId(Long routeId, Long companyId);

    Route createRoute(Long companyId, Route route);

    void delete(Long companyId, Long routeId);
}
