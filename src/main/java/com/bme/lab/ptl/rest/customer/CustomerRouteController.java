package com.bme.lab.ptl.rest.customer;
import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.service.route.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/3/21
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerRouteController {

    private final RouteService routeService;

    @GetMapping("/routes/from/{origin}/to/{destination}")
    public Page<Route> findByOriginAndDestination(@PathVariable(value="origin") String origin,
                                                  @PathVariable(value="destination") String destination,
                                                  Pageable pageable) {
        return routeService.findByOriginAndDestination(origin, destination, pageable);
    }

    @GetMapping("/routes/capacity/{capacity}")
    public Page<Route> findByLoadCapacity(@PathVariable(value="capacity") Double capacity,
                                          Pageable pageable) {
        return routeService.findRouteByLoadCapacityLessThanEqual(capacity, pageable);
    }
}
