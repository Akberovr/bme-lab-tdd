package com.bme.lab.ptl.rest.management;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 3/20/21
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/management")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/companies")
    @PreAuthorize("hasAnyAuthority('company:read')")
    public Iterable<Company> findAll() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{id}")
    @PreAuthorize("hasAnyAuthority('company:read')")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return companyService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/companies")
    @PreAuthorize("hasAnyAuthority('company:write')")
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody @Valid Company company) {
        return companyService.createCompany(company);
    }

    @DeleteMapping("/companies/{companyId}")
    @PreAuthorize("hasAnyAuthority('company:delete')")
    public ResponseEntity<?> deleteCompany(@PathVariable Long companyId) {
        companyService.delete(companyId);
        return ResponseEntity.ok().build();
    }

}
