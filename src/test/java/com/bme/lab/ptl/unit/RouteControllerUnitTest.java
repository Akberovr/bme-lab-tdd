package com.bme.lab.ptl.unit;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.rest.RouteController;
import com.bme.lab.ptl.service.route.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.AdditionalAnswers.returnsSecondArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/13/21
 */
class RouteControllerUnitTest {

    private RouteController routeController;

    private RouteService routeService;

    private Route route;

    private Company company;

    @BeforeEach
    void initRouteController() {
        routeService = Mockito.mock(RouteService.class);
        routeController = new RouteController(routeService);
        company = new Company(1L, "KLM", "klm@gmail.com");
        route = new Route(1L, "Baku", "Budapest", company);
    }

    @Test
    void shouldGetRoutes() {
        Iterable<Route> result = routeService.findAll();
        when(routeService.findAll()).thenReturn(result);
        assertEquals(result, routeController.getRoute());
    }

    @Test
    void shouldGetRouteByCompanyId() {
        Long routeId = route.getId();
        Long companyId = company.getId();
        when(routeService.findByIdAndCompanyId(routeId, companyId))
                .thenReturn(Optional.of(route));

        assertEquals(routeController.getRouteByIdAndCompanyId(routeId, companyId), new ResponseEntity<>(route, HttpStatus.OK));
    }

    @Test
    void shouldCreateRoute() {
        when(routeService.createRoute(any(Long.class), any(Route.class)))
                .then(returnsSecondArg());
        Route savedRoute = routeController.create(company.getId(), route);
        assertNotEquals(null, savedRoute);
    }

}
