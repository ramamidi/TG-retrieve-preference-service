package com.rama.retrievepreference.service;

import com.rama.retrievepreference.dto.GetMarketingPreferenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing {@link com.rama.retrievepreference.entity.MarketingPreference}.
 */
public interface MarketingPreferenceService {

    /**
     * Get all the MarketingPreferences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
     Page<GetMarketingPreferenceDTO> findAll(Pageable pageable);

    /**
     * Get one MarketingPreference by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
     Optional<GetMarketingPreferenceDTO> findOne(Integer id);
}
