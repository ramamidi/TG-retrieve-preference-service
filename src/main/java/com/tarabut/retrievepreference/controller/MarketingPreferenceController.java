package com.tarabut.retrievepreference.controller;

import com.tarabut.retrievepreference.dto.GetMarketingPreferenceDTO;
import com.tarabut.retrievepreference.entity.MarketingPreference;
import com.tarabut.retrievepreference.service.MarketingPreferenceService;
import com.tarabut.retrievepreference.service.impl.MarketingPreferenceServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@Api("Marketing Preference API")
public class MarketingPreferenceController {

    private final Logger log = LoggerFactory.getLogger(MarketingPreferenceController.class);

    private MarketingPreferenceService marketingPreferenceServiceImpl;

    @Autowired
    public MarketingPreferenceController(final MarketingPreferenceServiceImpl marketingPreferenceService) {
        this.marketingPreferenceServiceImpl = marketingPreferenceService;
    }

    /**
     * {@code GET  /marketing-preferences} : get all the MarketingPreference.
     *
     * @param pageIndex the pagination information.
     * @param pageSize  the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the lis of MarketingPreferences in the body.
     */
    @ApiOperation("Fetches Marketing Preferences based on page index and page size")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retreived successfully"),
            @ApiResponse(code = 500, message = "Internal Server error")

    })
    @GetMapping("/marketing-preferences")
    public ResponseEntity<List<GetMarketingPreferenceDTO>> getAllMarketingPreferences(@RequestParam("page") int pageIndex,
                                                                                      @RequestParam("size") int pageSize) {
        log.debug("REST request to get a page of Questions");
        Page<GetMarketingPreferenceDTO> page = marketingPreferenceServiceImpl.findAll(PageRequest.of(pageIndex, pageSize));
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET  /marketing-preferences/:id} : get the "id" marketing-preferences.
     *
     * @param id the id of the marketing-preferences to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
     * the marketing-preferences, or with status {@code 404 (Not Found)}.
     */
    @ApiOperation("Gets Marketing preference for an id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retreived successfully"),
            @ApiResponse(code = 500, message = "Internal Server error")

    })
    @GetMapping("/marketing-preferences/{id}")
    public ResponseEntity<GetMarketingPreferenceDTO> getMarketingPreference(@PathVariable Integer id) {
        log.debug("REST request to get Questions : {}", id);
        GetMarketingPreferenceDTO result = marketingPreferenceServiceImpl.findOne(id).
                orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Unable to find resource"));
        return ResponseEntity.ok().body(result);
    }
}
