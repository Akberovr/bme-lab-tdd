package com.bme.lab.ptl.unit;

import com.bme.lab.ptl.domain.Company;
import com.bme.lab.ptl.domain.Rating;
import com.bme.lab.ptl.rest.customer.CustomerCompanyController;
import com.bme.lab.ptl.service.rating.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.AdditionalAnswers.returnsSecondArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/19/21
 */
public class CustomerCompanyControllerUnitTest {


    private CustomerCompanyController customerCompanyController;

    private RatingService ratingService;

    private Company company;

    private List<Rating> ratings;

    @BeforeEach
    void initCompanyController() {
        ratingService = Mockito.mock(RatingService.class);
        customerCompanyController = new CustomerCompanyController(ratingService);
        company = new Company(1L, "KLM", "klm@gmail.com");
        ratings = List.of(new Rating(1L, 5, "Great"));
    }

    @Test
    void shouldGetRatingByCompanies() {
        Page<Rating> page = new PageImpl<>(ratings);
        when(ratingService.findRatingByCompanyId(
                eq(company.getId()),
                any(Pageable.class))
        ).thenReturn(page);

        assertEquals(page, customerCompanyController.findRatingByCompanyId(company.getId(), Pageable.unpaged()));
    }


    @Test
    void shouldCreateCompany() {
        when(ratingService.createRating(eq(company.getId()),  any(Rating.class)))
                .then(returnsSecondArg());
        Rating savedRating = customerCompanyController.rateCompany(company.getId(), ratings.get(0));
        assertNotEquals(null, savedRating);
    }

}
