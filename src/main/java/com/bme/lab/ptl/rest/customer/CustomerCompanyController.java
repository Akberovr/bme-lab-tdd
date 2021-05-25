package com.bme.lab.ptl.rest.customer;

import com.bme.lab.ptl.domain.Rating;
import com.bme.lab.ptl.service.rating.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/3/21
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CustomerCompanyController {

    private final RatingService ratingService;

    @GetMapping("/companies/id/{companyId}/ratings")
    public Page<Rating> findRatingByCompanyId(@PathVariable(value ="companyId") Long companyId,
                                              Pageable pageable) {
        return ratingService.findRatingByCompanyId(companyId, pageable);
    }

    @PostMapping("/companies/id/{companyId}/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    public Rating rateCompany(@PathVariable(value = "companyId") Long companyId,
                              @RequestBody @Valid Rating rating) {
        return ratingService.createRating(companyId, rating);
    }
}
