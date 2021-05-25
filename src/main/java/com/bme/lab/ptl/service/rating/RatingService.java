package com.bme.lab.ptl.service.rating;

import com.bme.lab.ptl.domain.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingService {
    Page<Rating> findRatingByCompanyId(Long companyId, Pageable pageable);

    Rating createRating(Long companyId, Rating rating);
}
