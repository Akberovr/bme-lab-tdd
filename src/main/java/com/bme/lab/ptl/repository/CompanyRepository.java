package com.bme.lab.ptl.repository;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    public Optional<Company> findByEmail(String email);
}
