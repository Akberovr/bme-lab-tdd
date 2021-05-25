package com.bme.lab.ptl.service.route;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.domain.Route;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface RouteService {
    Iterable<Route> findAll();

    Optional<Route> findByIdAndCompanyId(Long routeId, Long companyId);

    Page<Route> findByOriginAndDestination(String origin, String destination, Pageable pageable);

    Page<Route> findRouteByLoadCapacityLessThanEqual(Double capacity, Pageable pageable);

    Route createRoute(Long companyId, Route route);

    void delete(Long companyId, Long routeId);
}
