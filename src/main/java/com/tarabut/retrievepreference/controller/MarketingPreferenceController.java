package com.tarabut.retrievepreference.controller;

import com.tarabut.retrievepreference.entity.MarketingPreference;
import com.tarabut.retrievepreference.service.MarketingPreferenceService;
import com.tarabut.retrievepreference.service.impl.MarketingPreferenceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * REST controller for managing {@link MarketingPreference}.
 */
@RestController
public class MarketingPreferenceController {

    private final Logger log = LoggerFactory.getLogger(MarketingPreferenceController.class);

    private MarketingPreferenceService marketingPreferenceService;

    @Autowired
    public MarketingPreferenceController(final MarketingPreferenceService marketingPreferenceService) {
        this.marketingPreferenceService = marketingPreferenceService;
    }

    /**
     * {@code GET  /marketing-preferences} : get all the MarketingPreference.
     *
     * @param pageIndex the pagination information.
     * @param pageSize  the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the lis of MarketingPreferences in the body.
     */
    @GetMapping("/marketing-preferences")
    public ResponseEntity<List<MarketingPreference>> getAllMarketingPreferences(@RequestParam("page") int pageIndex,
                                                                                @RequestParam("size") int pageSize) {
        log.debug("REST request to get a page of Questions");
        Page<MarketingPreference> page = marketingPreferenceService.findAll(PageRequest.of(pageIndex, pageSize));
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /marketing-preferences/:id} : get marketing-preference id.
     *
     * @param id the id of the marketing-preference to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     * the marketing-preferences, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/marketing-preferences/{id}")
    public ResponseEntity<MarketingPreference> getOne(@PathVariable Integer id) {
        log.debug("REST request to get Questions : {}", id);
        MarketingPreference result = marketingPreferenceService.findOne(id).
                orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"));
        return ResponseEntity.ok().body(result);
    }
}
