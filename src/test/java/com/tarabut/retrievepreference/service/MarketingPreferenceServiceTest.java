package com.tarabut.retrievepreference.service;

import com.tarabut.retrievepreference.entity.MarketingPreference;
import com.tarabut.retrievepreference.repository.MarketingPreferenceRepository;
import com.tarabut.retrievepreference.service.impl.MarketingPreferenceServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MarketingPreferenceServiceTest {

    private static MarketingPreferenceService marketingPreferenceService;
    private static MarketingPreferenceRepository marketingPreferenceRepository;

    @BeforeAll
    public static void setupOnce() {
        marketingPreferenceRepository = mock(MarketingPreferenceRepository.class);
        marketingPreferenceService = new MarketingPreferenceServiceImpl(marketingPreferenceRepository);
    }

    @Test
    public void shouldAbleToFindAllWithPageable() {
        // Prepare
        Pageable pageable = mock(Pageable.class);
        List<MarketingPreference> marketingPreferenceList = Arrays.asList(new MarketingPreference());
        Page<MarketingPreference> page = new PageImpl<MarketingPreference>(marketingPreferenceList);
        when(marketingPreferenceRepository.findAll(eq(pageable))).thenReturn(page);

        // Act
        Page<MarketingPreference> results = marketingPreferenceService.findAll(pageable);

        // Assert
        assertThat(results.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void shouldAbleToFindOne() {
        // Prepare
        when(marketingPreferenceRepository.findById(eq(1))).thenReturn(Optional.of(new MarketingPreference()));

        // Act
        Optional<MarketingPreference> optionalMarketingPreference = marketingPreferenceService.findOne(1);

        // Assert
        assertThat(optionalMarketingPreference.isPresent()).isTrue();
    }

    @Test
    public void shouldReturnEmptyForNonExistentId() {
        // Prepare
        when(marketingPreferenceRepository.findById(eq(2))).thenReturn(Optional.empty());

        // Act
        Optional<MarketingPreference> optionalMarketingPreference = marketingPreferenceService.findOne(1);

        // Assert
        assertThat(optionalMarketingPreference.isPresent()).isFalse();
    }

}
