package com.bme.lab.ptl.service.rating;

import com.bme.lab.ptl.domain.Rating;
import com.bme.lab.ptl.exception.ResourceNotFoundException;
import com.bme.lab.ptl.repository.CompanyRepository;
import com.bme.lab.ptl.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author akberovr (Rovshan Akbarov)
 * created on 5/19/21
 */

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private  RatingRepository ratingRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Page<Rating> findRatingByCompanyId(Long companyId, Pageable pageable) {
        return ratingRepository.findRatingByCompanyId(companyId, pageable);
    }

    @Override
    public Rating createRating(Long companyId, Rating rating) {
        return companyRepository.findById(companyId)
                .map(company -> {
                    rating.setCompany(company);
                    return ratingRepository.save(rating);
                }).orElseThrow(() -> new ResourceNotFoundException("RatingId " + rating.getId() + "not found"));
    }
}
