package com.bme.lab.ptl.service.company;

import com.bme.lab.ptl.domain.Company;

import java.awt.print.Pageable;
import java.util.Optional;

public interface CompanyService {
    Iterable<Company> findAll();

    Optional<Company> findById(Long companyId);

    Company createCompany(Company company);

    void delete(Long companyId);
}
