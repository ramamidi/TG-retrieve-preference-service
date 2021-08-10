package com.tarabut.retrievepreference.repository;

import com.tarabut.retrievepreference.entity.MarketingPreference;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data  repository for the MarketingPreference entity.
 */
public interface MarketingPreferenceRepository extends JpaRepository<MarketingPreference, Integer> {
}


