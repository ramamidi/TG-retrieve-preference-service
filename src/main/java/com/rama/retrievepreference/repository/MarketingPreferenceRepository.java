package com.rama.retrievepreference.repository;

import com.rama.retrievepreference.entity.MarketingPreference;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data  repository for the MarketingPreference entity.
 */
public interface MarketingPreferenceRepository extends JpaRepository<MarketingPreference, Integer> {
}


