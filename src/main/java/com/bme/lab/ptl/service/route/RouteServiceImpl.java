package com.bme.lab.ptl.service.route;

import com.bme.lab.ptl.exception.ResourceNotFoundException;
import com.bme.lab.ptl.domain.Route;
import com.bme.lab.ptl.repository.CompanyRepository;
import com.bme.lab.ptl.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 2020-10-21
 */

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    private final CompanyRepository companyRepository;

    public Iterable<Route> findAll() {
        return routeRepository.findAll();
    }

    @Override
    public Optional<Route> findByIdAndCompanyId(Long routeId, Long companyId) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("CompanyId " + companyId + " not found");
        }
        return routeRepository.findByIdAndCompanyId(routeId, companyId);
    }

    @Override
    public Route createRoute(Long companyId, Route route) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    route.setCompany(company);
                    return routeRepository.save(route);
                }).orElseThrow(() -> new ResourceNotFoundException("RouteId " + route.getId() + "not found"));
    }

    @Override
    public void delete(Long companyId, Long routeId) {
        routeRepository.findByIdAndCompanyId(routeId, companyId)
                .map(route -> {
                    routeRepository.delete(route);
                    return "Deleted route id- " + routeId;
                }).orElseThrow(() -> new ResourceNotFoundException("Route not found with id " + routeId + " and companyId " + companyId));
    }

}
