package com.bme.lab.ptl.repository;

import com.bme.lab.ptl.domain.Rating;
import com.bme.lab.ptl.domain.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    Page<Rating> findRatingByCompanyId(Long companyId, Pageable pageable);
}
