package com.bme.lab.ptl.unit;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.domain.Rating;
import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.rest.customer.CustomerRouteController;
import com.bme.lab.ptl.service.route.RouteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/19/21
 */
public class CustomerRouteControllerUnitTest {

    private CustomerRouteController customerRouteController;

    private RouteService routeService;

    private Company company;

    private Route route;

    private List<Route> routes;
    @BeforeEach
    void initCompanyController() {
        routeService = Mockito.mock(RouteService.class);
        customerRouteController = new CustomerRouteController(routeService);
        company = new Company(1L, "KLM", "klm@gmail.com");
        route = new Route(1L, "Budapest", "London", 10.0, company);
        routes = List.of(route);

    }

    @Test
    void shouldFindRouteByOriginAndDestination() {
        Page<Route> page = new PageImpl<>(routes);
        when(routeService.findByOriginAndDestination(
                eq(route.getOrigin()),
                eq(route.getDestination()),
                any(Pageable.class))
        ).thenReturn(page);

        assertEquals(page, customerRouteController.findByOriginAndDestination(
                route.getOrigin(),
                route.getDestination(),
                Pageable.unpaged())
        );
    }

    @Test
    void shouldFindRouteByLoadCapacity() {
        Page<Route> page = new PageImpl<>(routes);
        when(routeService.findRouteByLoadCapacityLessThanEqual(
                any(Double.class),
                any(Pageable.class)
        )).thenReturn(page);

        assertEquals(page, customerRouteController.findByLoadCapacity(
                route.getLoadCapacity(),
                Pageable.unpaged())
        );
    }


}
