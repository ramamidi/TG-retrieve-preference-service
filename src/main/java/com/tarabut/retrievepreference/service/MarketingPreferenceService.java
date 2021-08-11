package com.tarabut.retrievepreference.service;

import com.tarabut.retrievepreference.entity.MarketingPreference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing {@link com.tarabut.retrievepreference.entity.MarketingPreference}.
 */
public interface MarketingPreferenceService {

    /**
     * Get all the MarketingPreferences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<MarketingPreference> findAll(Pageable pageable);

    /**
     * Get one MarketingPreference by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<MarketingPreference> findOne(Integer id);
}
