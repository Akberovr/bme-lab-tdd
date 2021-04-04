package com.bme.lab.ptl.service.company;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/20/21
 */
@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Long companyId) {
        return companyRepository.findById(companyId);
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long companyId) {
        companyRepository.findById(companyId)
                .map(company -> {
                    companyRepository.delete(company);
                    return companyId;
                });
    }
}
