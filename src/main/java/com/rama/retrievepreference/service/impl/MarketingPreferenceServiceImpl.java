package com.rama.retrievepreference.service.impl;

import com.rama.retrievepreference.dto.GetMarketingPreferenceDTO;
import com.rama.retrievepreference.entity.MarketingPreference;
import com.rama.retrievepreference.repository.MarketingPreferenceRepository;
import com.rama.retrievepreference.service.MarketingPreferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link com.rama.retrievepreference.entity.MarketingPreference}.
 */
@Service
public class MarketingPreferenceServiceImpl implements MarketingPreferenceService {

    private final Logger log = LoggerFactory.getLogger(MarketingPreferenceServiceImpl.class);
    private final MarketingPreferenceRepository marketingPreferenceRepository;

    @Autowired
    public MarketingPreferenceServiceImpl(final MarketingPreferenceRepository marketingPreferenceRepository) {
        this.marketingPreferenceRepository = marketingPreferenceRepository;
    }

    /**
     * Get all the MarketingPreferences.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Page<GetMarketingPreferenceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Criteria");
        return marketingPreferenceRepository.findAll(pageable).map(entity -> entity.toGetMarketingPreferenceDTO().get());
    }

    /**
     * Get one MarketingPreference by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Optional<GetMarketingPreferenceDTO> findOne(Integer id) {
        log.debug("Request to get Criteria : {}", id);
        Optional<MarketingPreference> optionalMarketingPreference = marketingPreferenceRepository.findById(id);
        Optional<GetMarketingPreferenceDTO> optionalGetMarketingPreferenceDTO = Optional.empty();
        if(optionalMarketingPreference.isPresent()) {
            optionalGetMarketingPreferenceDTO = optionalMarketingPreference.get().toGetMarketingPreferenceDTO();
        }
        return optionalGetMarketingPreferenceDTO;
    }
}
