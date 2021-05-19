package com.bme.lab.ptl.rest;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/20/21
 */

@RestController
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies")
    public Iterable<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody @Valid Company company) {
        return companyService.createCompany(company);
    }

    @DeleteMapping("/companies/{companyId}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.ok().build();
    }

}
