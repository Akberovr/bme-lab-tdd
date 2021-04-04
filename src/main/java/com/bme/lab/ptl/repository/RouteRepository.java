package com.bme.lab.ptl.repository;

import com.bme.lab.ptl.domain.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer> {
    Optional<Route> findByIdAndCompanyId(Long id, Long companyId);
}
