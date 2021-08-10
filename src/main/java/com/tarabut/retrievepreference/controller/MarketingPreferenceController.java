package com.tarabut.retrievepreference.controller;

import com.tarabut.retrievepreference.entity.MarketingPreference;
import com.tarabut.retrievepreference.errors.NotFoundException;
import com.tarabut.retrievepreference.service.MarketingPreferenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link MarketingPreference}.
 */
@RestController
public class MarketingPreferenceController {

    private final Logger log = LoggerFactory.getLogger(MarketingPreferenceController.class);

    @Autowired
    private MarketingPreferenceService marketingPreferenceService;

    /**
     * {@code GET  /marketing-preferences} : get all the MarketingPreference.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the lis of MarketingPreferences in the body.
     */
    @GetMapping("/marketing-preferences")
    public ResponseEntity<List<MarketingPreference>> getAllMarketingPreferences(Pageable pageable) {
        log.debug("REST request to get a page of Questions");
        Page<MarketingPreference> page = marketingPreferenceService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /marketing-preferences/:id} : get the "id" marketing-preferences.
     *
     * @param id the id of the marketing-preferences to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     *         the marketing-preferences, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/marketing-preferences/{id}")
    public ResponseEntity<MarketingPreference> getQuestions(@PathVariable Integer id) {
        log.debug("REST request to get Questions : {}", id);
        MarketingPreference result = marketingPreferenceService.findOne(id).
                orElseThrow(() -> new NotFoundException("Not found"));
        return  ResponseEntity.ok().body(result);
    }
}
