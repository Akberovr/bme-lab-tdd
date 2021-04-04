package com.bme.lab.ptl.unit;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.rest.CompanyController;
import com.bme.lab.ptl.service.company.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/19/21
 */
class CompanyControllerUnitTest {

    private CompanyController companyController;

    private CompanyService companyService;

    private Company company;

    @BeforeEach
    void initCompanyController() {
        companyService = Mockito.mock(CompanyService.class);
        companyController = new CompanyController(companyService);
        company = new Company(1L, "KLM", "klm@gmail.com");
    }

    @Test
    void shouldGetCompanies() {
        Iterable<Company> result = companyService.findAll();

        when(companyService.findAll()).thenReturn(result);
        assertEquals(result, companyController.findAll());
    }

    @Test
    void shouldGetCompanyById() {
        when(companyService.findById(company.getId()))
                .thenReturn(Optional.of(company));

        assertEquals(new ResponseEntity<>(company, HttpStatus.OK), companyController.getCompanyById(company.getId()));
    }

    @Test
    void shouldCreateCompany() {
        when(companyService.createCompany(any(Company.class)))
                .then(returnsFirstArg());
        Company savedCompany = companyController.create(company);
        assertNotEquals(null, savedCompany);
    }
}
